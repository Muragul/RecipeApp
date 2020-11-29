package com.example.recipeapp.data.model.bar

import com.google.gson.annotations.SerializedName

data class BarCategoryResponse(
    @SerializedName("drinks")
    val barCategories: List<BarCategory>
)