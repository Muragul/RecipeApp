package com.example.recipeapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.repository.food.RandomRecipeRepository

class RandomRecipeDataStore(apiService: ApiService) : RandomRecipeRepository,
    BaseRandomDataStore(apiService) {
    override fun loadData(): LiveData<MealResponse> {
        return fetchData { service.getRandomRecipeAsync() }
    }
}