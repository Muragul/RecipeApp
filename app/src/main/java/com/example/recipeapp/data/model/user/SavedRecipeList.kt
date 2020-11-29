package com.example.recipeapp.data.model.user

import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.food.Meal

object SavedRecipeList {
    val foodRecipeList: MutableList<Meal> = ArrayList()
    val barRecipeList: MutableList<Drink> = ArrayList()

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