package com.example.recipeapp.data.model.user

import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.food.Meal

object SavedRecipeList {
    var foodRecipeList: MutableList<Meal> = ArrayList()
    var barRecipeList: MutableList<Drink> = ArrayList()

    fun clearRecipeList(){
        foodRecipeList = ArrayList()
        barRecipeList = ArrayList()
    }

    fun initFoodRecipeList(foodList: ArrayList<Meal>){
        foodRecipeList = foodList
    }

    fun initBarRecipeList(barList: ArrayList<Drink>){
        barRecipeList = barList
    }

    fun addFoodRecipe(recipe: Meal) {
        foodRecipeList.add(recipe)
    }

    fun addBarRecipe(recipe: Drink) {
        barRecipeList.add(recipe)
    }

    fun checkFoodRecipeInList(recipe: Meal): Boolean {
        if (foodRecipeList.contains(recipe)) return true
        return false
    }

    fun checkBarRecipeInList(recipe: Drink): Boolean {
        if (barRecipeList.contains(recipe)) return true
        return false
    }

    fun removeFoodRecipeFromList(recipe: Meal) {
        foodRecipeList.remove(recipe)
    }

    fun removeBarRecipeFromList(recipe: Drink) {
        barRecipeList.remove(recipe)
    }
}