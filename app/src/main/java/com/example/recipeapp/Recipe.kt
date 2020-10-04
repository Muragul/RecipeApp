package com.example.recipeapp

import com.google.gson.annotations.SerializedName

data class Recipe (
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
    @SerializedName("idMeal")
    val idMeal: String
    )

data class RecipeResponse(
    @SerializedName("meals")
    val meals: List<Recipe>
)