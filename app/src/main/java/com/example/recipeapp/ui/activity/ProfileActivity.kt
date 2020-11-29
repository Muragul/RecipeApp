package com.example.recipeapp.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.recipeapp.R
import com.example.recipeapp.ui.fragment.BarFragment
import com.example.recipeapp.ui.fragment.FoodFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        username.text = sharedPreferences.getString("current_user_name", null)
        email.text = sharedPreferences.getString("current_email", null)
        back.setOnClickListener { onBackPressed() }
        logout_button.setOnClickListener {
            val userEditor = sharedPreferences.edit()
            userEditor.clear()
            userEditor.apply()
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FoodFragment(), "Food Recipes")
        adapter.addFragment(BarFragment(), "Cocktail Recipes")
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
}