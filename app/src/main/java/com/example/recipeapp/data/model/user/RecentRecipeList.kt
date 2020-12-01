package com.example.recipeapp.data.model.user

import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.food.Meal

object RecentRecipeList {
    val recentFoodRecipeList: MutableList<Meal> = ArrayList()
    val recentBarRecipeList: MutableList<Drink> = ArrayList()

    fun addFoodRecipe(recipe: Meal) {
        recentFoodRecipeList.add(recipe)
        checkFoodList()
    }

    fun addBarRecipe(recipe: Drink) {
        recentBarRecipeList.add(recipe)
        checkBarList()
    }

    private fun removeLastFoodRecipeFromList() {
        recentFoodRecipeList.removeAt(0)
    }

    private fun removeLastBarRecipeFromList() {
        recentBarRecipeList.removeAt(0)
    }

    private fun checkFoodList() {
        if (recentFoodRecipeList.size > 10)
            removeLastFoodRecipeFromList()
    }

    private fun checkBarList() {
        if (recentBarRecipeList.size > 10)
            removeLastBarRecipeFromList()
    }


}