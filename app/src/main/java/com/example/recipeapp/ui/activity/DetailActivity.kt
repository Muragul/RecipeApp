package com.example.recipeapp.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.viewmodel.RecipeDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id: String = intent.getStringExtra("idMeal")!!
        val image: ImageView = findViewById(R.id.image)
        val title: TextView = findViewById(R.id.title)
        val instructions: TextView = findViewById(R.id.instructions)
        val back: ImageView = findViewById(R.id.back)
        val category: TextView = findViewById(R.id.category)
        val country: TextView = findViewById(R.id.country)
        val ingredients: TextView = findViewById(R.id.ingredients)

        val decorView: View = window.decorView
        val options: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = options

        back.setOnClickListener {
            onBackPressed()
        }

        try {
            recipeDetailsViewModel.getRecipeDetails(id)
            recipeDetailsViewModel.getRecipeDetails(id).observe(this, Observer {
                val response = it.meals[0]
                title.text = response.strMeal
                Glide.with(this).load(response.strMealThumb).into(image)
                instructions.text = response.strInstructions
                category.text = response.strCategory
                country.text = response.strArea
                ingredients.text =
                    response.strIngredient1 + response.strMeasure1 + "\n" +
                            response.strIngredient2 + response.strMeasure2 + "\n" +
                            response.strIngredient3 + response.strMeasure3 + "\n" +
                            response.strIngredient4 + response.strMeasure4 + "\n" +
                            response.strIngredient5 + response.strMeasure5 + "\n" +
                            response.strIngredient6 + response.strMeasure6 + "\n" +
                            response.strIngredient7 + response.strMeasure7 + "\n" +
                            response.strIngredient8 + response.strMeasure8 + "\n" +
                            response.strIngredient9 + response.strMeasure9 + "\n" +
                            response.strIngredient10 + response.strMeasure10 + "\n" +
                            response.strIngredient11 + response.strMeasure11 + "\n" +
                            response.strIngredient12 + response.strMeasure12 + "\n"
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Error connection", Toast.LENGTH_SHORT).show()
        }

    }
}