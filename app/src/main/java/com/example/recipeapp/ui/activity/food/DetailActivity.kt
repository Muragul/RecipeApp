package com.example.recipeapp.ui.activity.food

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.gson.Gson
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

                val sharedPreferences: SharedPreferences =
                    this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
                val username = sharedPreferences.getString("current_user_name", null)
                val sharedPreferencesRecipeLists: SharedPreferences =
                    this.getSharedPreferences(username, Context.MODE_PRIVATE)
                val userEditor = sharedPreferencesRecipeLists.edit()

                val recentFoodList = Gson().toJson(RecentRecipeList.recentFoodRecipeList)
                userEditor.putString("recent_food_recipe_list", recentFoodList)
                userEditor.apply()

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

                    val foodList = Gson().toJson(SavedRecipeList.foodRecipeList)
                    userEditor.putString("food_recipe_list", foodList)
                    userEditor.apply()

                }
                title.text = response.strMeal
                Glide.with(this).load(response.strMealThumb).into(image)
                instructions.text = response.strInstructions
                category.text = response.strCategory
                country.text = response.strArea
                var ingredientsText = ""
                if (response.strIngredient1 != null || response.strMeasure1 != null) {
                    ingredientsText += response.strIngredient1 + " " + response.strMeasure1 + "\n"
                    if (response.strIngredient2 != null || response.strMeasure2 != null) {
                        ingredientsText += response.strIngredient2 + " " + response.strMeasure2 + "\n"
                        if (response.strIngredient3 != null || response.strMeasure3 != null) {
                            ingredientsText += response.strIngredient3 + " " + response.strMeasure3 + "\n"
                            if (response.strIngredient4 != null || response.strMeasure4 != null) {
                                ingredientsText += response.strIngredient4 + " " + response.strMeasure4 + "\n"
                                if (response.strIngredient5 != null || response.strMeasure5 != null) {
                                    ingredientsText += response.strIngredient5 + " " + response.strMeasure5 + "\n"
                                    if (response.strIngredient6 != null || response.strMeasure6 != null) {
                                        ingredientsText += response.strIngredient6 + " " + response.strMeasure6 + "\n"
                                        if (response.strIngredient7 != null || response.strMeasure7 != null) {
                                            ingredientsText += response.strIngredient7 + " " + response.strMeasure7 + "\n"
                                            if (response.strIngredient8 != null || response.strMeasure8 != null) {
                                                ingredientsText += response.strIngredient8 + " " + response.strMeasure8 + "\n"
                                                if (response.strIngredient9 != null || response.strMeasure9 != null) {
                                                    ingredientsText += response.strIngredient9 + " " + response.strMeasure9 + "\n"
                                                    if (response.strIngredient10 != null || response.strMeasure10 != null) {
                                                        ingredientsText += response.strIngredient10 + " " + response.strMeasure10 + "\n"
                                                        if (response.strIngredient11 != null || response.strMeasure11 != null) {
                                                            ingredientsText += response.strIngredient11 + " " + response.strMeasure11 + "\n"
                                                            if (response.strIngredient12 != null || response.strMeasure12 != null)
                                                                ingredientsText += response.strIngredient12 + " " + response.strMeasure12 + "\n"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ingredients.text = ingredientsText
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Error connection", Toast.LENGTH_SHORT).show()
        }

        recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view.adapter = RecentRecipeAdapter(this)


    }

}