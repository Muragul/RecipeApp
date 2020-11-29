package com.example.recipeapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.domain.repository.RecipeListRepository

class RecipeListDataStore(apiService: ApiService) : RecipeListRepository,
    BaseRecipeDataStore(apiService) {
    override fun loadData(strCategory: String): LiveData<List<Recipe>> {
        return fetchData { service.getPostListByCategoryAsync(strCategory) }
    }
}