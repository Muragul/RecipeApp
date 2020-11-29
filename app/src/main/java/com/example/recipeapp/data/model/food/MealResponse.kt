package com.example.recipeapp.data.model.food

import com.google.gson.annotations.SerializedName


data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)