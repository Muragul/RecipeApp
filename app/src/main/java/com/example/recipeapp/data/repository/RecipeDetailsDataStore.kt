package com.example.recipeapp.data.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.data.model.MealResponse
import com.example.recipeapp.domain.repository.RecipeDetailsRepository

class RecipeDetailsDataStore(apiService: ApiService) : RecipeDetailsRepository,
    BaseRecipeDetailsDataStore(apiService) {
    override fun loadData(strMeal: String): LiveData<MealResponse> {
        return fetchData { service.getRecipeAsync(strMeal) }
    }
}