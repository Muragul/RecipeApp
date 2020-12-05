package com.example.recipeapp.ui.activity.bar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.user.RecentRecipeList
import com.example.recipeapp.data.model.user.SavedRecipeList
import com.example.recipeapp.ui.adapter.user.BarRecipeAdapter
import com.example.recipeapp.ui.adapter.user.RecentBarRecipeAdapter
import com.example.recipeapp.viewmodel.bar.BarRecipeDetailsViewModel
import kotlinx.android.synthetic.main.activity_bar_detail.save_icon
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class BarDetailActivity : AppCompatActivity(), BarRecipeAdapter.BarRecipeClickListener {
    private val barRecipeDetailsViewModel: BarRecipeDetailsViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_detail)

        val id: String = intent.getStringExtra("idDrink")!!
        val image: ImageView = findViewById(R.id.image)
        val title: TextView = findViewById(R.id.title)
        val instructions: TextView = findViewById(R.id.instructions)
        val back: ImageView = findViewById(R.id.back)
        val category: TextView = findViewById(R.id.category)
        val ingredients: TextView = findViewById(R.id.ingredients)

        val decorView: View = window.decorView
        val options: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = options

        back.setOnClickListener { onBackPressed() }

        try {
            barRecipeDetailsViewModel.getBarRecipeDetails(id)
            barRecipeDetailsViewModel.getBarRecipeDetails(id).observe(this, Observer {
                val response = it.drinks[0]
                RecentRecipeList.addBarRecipe(response)
                if (SavedRecipeList.checkBarRecipeInList(response))
                    Glide.with(this).load(R.drawable.saved_icon).into(save_icon)
                save_icon.setOnClickListener {
                    if (SavedRecipeList.checkBarRecipeInList(response)) {
                        Glide.with(this).load(R.drawable.save_icon).into(save_icon)
                        SavedRecipeList.removeBarRecipeFromList(response)
                    } else {
                        Glide.with(this).load(R.drawable.saved_icon).into(save_icon)
                        SavedRecipeList.addBarRecipe(response)
                    }
                }

                title.text = response.strDrink
                Glide.with(this).load(response.strDrinkThumb).into(image)
                instructions.text = response.strInstructions
                category.text = response.strCategory
                ingredients.text =
                    response.strIngredient1 + " " + response.strMeasure1 + "\n" +
                            response.strIngredient2 + " " + response.strMeasure2 + "\n" +
                            response.strIngredient3 + " " + response.strMeasure3 + "\n" +
                            response.strIngredient4 + " " + response.strMeasure4 + "\n" +
                            response.strIngredient5 + " " + response.strMeasure5 + "\n" +
                            response.strIngredient6 + " " + response.strMeasure6 + "\n" +
                            response.strIngredient7 + " " + response.strMeasure7 + "\n" +
                            response.strIngredient8 + " " + response.strMeasure8 + "\n" +
                            response.strIngredient9 + " " + response.strMeasure9 + "\n" +
                            response.strIngredient10 + " " + response.strMeasure10 + "\n" +
                            response.strIngredient11 + " " + response.strMeasure11 + "\n" +
                            response.strIngredient12 + " " + response.strMeasure12 + "\n"
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Error connection", Toast.LENGTH_SHORT).show()
        }

        recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view.adapter = RecentBarRecipeAdapter(this)


    }

    override fun barRecipeClicked(post: Drink) {
        val intent = Intent(this, BarDetailActivity::class.java)
        intent.putExtra("idDrink", post.idDrink)
        startActivity(intent)

    }

}