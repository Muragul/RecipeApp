package com.example.recipeapp.ui.activity.bar

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_bar_detail.*
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

                val sharedPreferences: SharedPreferences =
                    this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
                val username = sharedPreferences.getString("current_user_name", null)
                val sharedPreferencesRecipeLists: SharedPreferences =
                    this.getSharedPreferences(username, Context.MODE_PRIVATE)
                val userEditor = sharedPreferencesRecipeLists.edit()

                val recentBarList = Gson().toJson(RecentRecipeList.recentBarRecipeList)
                userEditor.putString("recent_bar_recipe_list", recentBarList)
                userEditor.apply()

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
                    val barList = Gson().toJson(SavedRecipeList.barRecipeList)
                    userEditor.putString("bar_recipe_list", barList)
                    userEditor.apply()
                }

                title.text = response.strDrink
                Glide.with(this).load(response.strDrinkThumb).into(image)
                instructions.text = response.strInstructions
                category.text = response.strCategory
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
        recycler_view.adapter = RecentBarRecipeAdapter(this)


    }

    override fun barRecipeClicked(post: Drink) {
        val intent = Intent(this, BarDetailActivity::class.java)
        intent.putExtra("idDrink", post.idDrink)
        startActivity(intent)

    }

}