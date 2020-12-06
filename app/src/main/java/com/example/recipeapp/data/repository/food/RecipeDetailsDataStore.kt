package com.example.recipeapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.repository.food.RecipeDetailsRepository

class RecipeDetailsDataStore(apiService: ApiService) : RecipeDetailsRepository,
    BaseRecipeDetailsDataStore(apiService) {
    override fun loadData(strMeal: String): LiveData<MealResponse> {
        return fetchData { service.getRecipeAsync(strMeal) }
    }
}