package com.example.recipeapp.data.model.bar

import com.example.recipeapp.data.model.food.Recipe
import com.google.gson.annotations.SerializedName

data class BarRecipeResponse(
    @SerializedName("drinks")
    val drinks: List<BarRecipe>
)