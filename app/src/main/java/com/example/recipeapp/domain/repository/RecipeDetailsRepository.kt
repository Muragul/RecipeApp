package com.example.recipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.MealResponse

interface RecipeDetailsRepository {
    fun loadData(strMeal: String): LiveData<MealResponse>
}