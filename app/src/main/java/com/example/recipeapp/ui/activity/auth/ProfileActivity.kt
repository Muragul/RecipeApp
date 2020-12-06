package com.example.recipeapp.ui.activity.auth

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.food.Meal
import com.example.recipeapp.data.model.user.RecentRecipeList
import com.example.recipeapp.data.model.user.RequestConstants
import com.example.recipeapp.data.model.user.SavedRecipeList
import com.example.recipeapp.ui.activity.StartActivity
import com.example.recipeapp.ui.activity.bar.BarDetailActivity
import com.example.recipeapp.ui.activity.food.DetailActivity
import com.example.recipeapp.ui.adapter.user.BarRecipeAdapter
import com.example.recipeapp.ui.adapter.user.FoodRecipeAdapter
import com.example.recipeapp.ui.fragment.user.BarFragment
import com.example.recipeapp.ui.fragment.user.FoodFragment
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.File

class ProfileActivity : AppCompatActivity(), FoodRecipeAdapter.FoodRecipeClickListener,
    BarRecipeAdapter.BarRecipeClickListener {
    private var selectedPhotoFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        username.text = sharedPreferences.getString("current_user_name", null)
        email.text = sharedPreferences.getString("current_email", null)
        change_button.setOnClickListener {
            getPermissions()
        }
        back.setOnClickListener { onBackPressed() }
        logout_button.setOnClickListener {
            val userEditor = sharedPreferences.edit()
            userEditor.clear()
            userEditor.apply()
            SavedRecipeList.clearRecipeList()
            RecentRecipeList.clearRecipeList()
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FoodFragment(this), "Food Recipes")
        adapter.addFragment(BarFragment(this), "Cocktail Recipes")
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

    }

    class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titleList[position]
        }

    }

    override fun foodRecipeClicked(post: Meal) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idMeal", post.idMeal)
        startActivity(intent)
    }

    override fun barRecipeClicked(post: Drink) {
        val intent = Intent(this, BarDetailActivity::class.java)
        intent.putExtra("idDrink", post.idDrink)
        startActivity(intent)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val imageFile = File.createTempFile("avatar", ".jpg", cacheDir)
        val avatarUri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            imageFile
        )
        selectedPhotoFile = imageFile
        intent.putExtra(MediaStore.EXTRA_OUTPUT, avatarUri)
        startActivityForResult(intent, RequestConstants.CAMERA)
    }

    private fun getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            val galleryGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            if (cameraGranted && galleryGranted) {
                imageChooserDialog()
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
                    RequestConstants.AVATAR_PERMISSION_REQUEST
                )
            }
        } else {
            imageChooserDialog()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RequestConstants.AVATAR_CAMERA_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }
            return
        } else if (requestCode == RequestConstants.AVATAR_GALLERY_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            }
            return
        } else if (requestCode == RequestConstants.AVATAR_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageChooserDialog()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RequestConstants.CAMERA) {
                selectedPhotoFile?.let { file ->
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(
                            contentResolver,
                            Uri.parse("file:${file.absolutePath}")
                        )
                    imageView.setImageBitmap(bitmap)
                }
            } else if (requestCode == RequestConstants.GALLERY) {
                val image = data?.data
                imageView.setImageURI(image)
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        startActivityForResult(intent, RequestConstants.GALLERY)
    }

    private fun imageChooserDialog() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        adapter.add("Camera")
        adapter.add("Gallery")
        adapter.add("Remove")
        AlertDialog.Builder(this)
            .setTitle("Change avatar")
            .setAdapter(adapter) { dialog, which ->
                if (which == 0) {
                    openCamera()
                } else
                    if (which == 1) {
                        openGallery()
                    } else {
                        deletePhoto()
                    }
            }
            .create()
            .show()
    }

    private fun deletePhoto() {
        Glide.with(this).load(R.drawable.app_icon).into(imageView)
    }

}