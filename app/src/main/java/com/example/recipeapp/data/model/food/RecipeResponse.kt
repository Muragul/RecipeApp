package com.example.recipeapp.data.model.food

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("meals")
    val meals: List<Recipe>
)