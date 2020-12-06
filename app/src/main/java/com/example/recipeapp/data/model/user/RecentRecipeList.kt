package com.example.recipeapp.data.model.user

import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.food.Meal

object RecentRecipeList {
    var recentFoodRecipeList: MutableList<Meal> = ArrayList()
    var recentBarRecipeList: MutableList<Drink> = ArrayList()

    fun clearRecipeList(){
        recentFoodRecipeList = ArrayList()
        recentBarRecipeList = ArrayList()
    }

    fun initFoodRecipeList(foodList: ArrayList<Meal>) {
        recentFoodRecipeList = foodList
    }

    fun initBarRecipeList(barList: ArrayList<Drink>) {
        recentBarRecipeList = barList
    }

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