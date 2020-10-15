package com.example.recipeapp.data.model

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("meals")
    val meals: List<Recipe>
)