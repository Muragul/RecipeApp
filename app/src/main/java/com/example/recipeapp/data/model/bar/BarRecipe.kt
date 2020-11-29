package com.example.recipeapp.data.model.bar

import com.google.gson.annotations.SerializedName

data class BarRecipe(
    @SerializedName("strDrink")
    val strDrink: String,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String,
    @SerializedName("idDrink")
    val idDrink: String
)