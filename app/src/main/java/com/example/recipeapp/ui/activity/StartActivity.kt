package com.example.recipeapp.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.bar.DrinkResponse
import com.example.recipeapp.data.model.food.Meal
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.data.model.user.RecentRecipeList
import com.example.recipeapp.data.model.user.SavedRecipeList
import com.example.recipeapp.ui.activity.auth.AuthActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("current_user_name", null)
        if (username != null) {
            initRecipeLists()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecipeLists() {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("current_user_name", null)
        val sharedPreferencesRecipeLists: SharedPreferences =
            this.getSharedPreferences(username, Context.MODE_PRIVATE)
        val foodListGson = sharedPreferencesRecipeLists.getString("food_recipe_list", null)
        val barListGson = sharedPreferencesRecipeLists.getString("bar_recipe_list", null)
        val recentFoodListGson =
            sharedPreferencesRecipeLists.getString("recent_food_recipe_list", null)
        val recentBarListGson =
            sharedPreferencesRecipeLists.getString("recent_bar_recipe_list", null)
        val gson = GsonBuilder().create()
        if (foodListGson != null) {
            val foodList = gson.fromJson(foodListGson, Array<Meal>::class.java).toMutableList()
            SavedRecipeList.initFoodRecipeList(foodList as ArrayList<Meal>)
        }
        if (barListGson != null) {
            val barList = gson.fromJson(barListGson, Array<Drink>::class.java).toMutableList()
            SavedRecipeList.initBarRecipeList(barList as ArrayList<Drink>)
        }
        if (recentFoodListGson != null) {
            val recentFoodList = gson.fromJson(recentFoodListGson, Array<Meal>::class.java).toMutableList()
            RecentRecipeList.initFoodRecipeList(recentFoodList as ArrayList<Meal>)
        }
        if (recentBarListGson != null) {
            val recentBarList = gson.fromJson(recentBarListGson, Array<Drink>::class.java).toMutableList()
            RecentRecipeList.initBarRecipeList(recentBarList as ArrayList<Drink>)
        }
    }
}