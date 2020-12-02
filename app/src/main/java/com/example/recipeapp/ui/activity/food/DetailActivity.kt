package com.example.recipeapp.ui.activity.food

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.food.Meal
import com.example.recipeapp.data.model.user.RecentRecipeList
import com.example.recipeapp.data.model.user.SavedRecipeList
import com.example.recipeapp.ui.adapter.user.FoodRecipeAdapter
import com.example.recipeapp.ui.adapter.user.RecentRecipeAdapter
import com.example.recipeapp.viewmodel.food.RecipeDetailsViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), FoodRecipeAdapter.FoodRecipeClickListener {
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModel()

    override fun foodRecipeClicked(post: Meal) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idMeal", post.idMeal)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
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
        back.setOnClickListener { onBackPressed() }
        video.settings.javaScriptEnabled = true
        video.webViewClient = WebViewClient()

        try {
            recipeDetailsViewModel.getRecipeDetails(id)
            recipeDetailsViewModel.getRecipeDetails(id).observe(this, Observer {
                val response = it.meals[0]
                RecentRecipeList.addFoodRecipe(response)
                video.loadUrl(response.strYoutube)
                if (SavedRecipeList.checkFoodRecipeInList(response))
                    Glide.with(this).load(R.drawable.saved_icon).into(save_icon)
                save_icon.setOnClickListener {
                    if (SavedRecipeList.checkFoodRecipeInList(response)) {
                        Glide.with(this).load(R.drawable.save_icon).into(save_icon)
                        SavedRecipeList.removeFoodRecipeFromList(response)
                    } else {
                        Glide.with(this).load(R.drawable.saved_icon).into(save_icon)
                        SavedRecipeList.addFoodRecipe(response)
                    }
                }
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

        recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view.adapter = RecentRecipeAdapter(this)


    }

}