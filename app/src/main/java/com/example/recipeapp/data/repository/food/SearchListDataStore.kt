package com.example.recipeapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.domain.repository.food.SearchRecipeListRepository

class SearchListDataStore(apiService: ApiService) : SearchRecipeListRepository,
    BaseSearchDataStore(apiService) {
    override fun loadData(strIngredient: String): LiveData<List<Recipe>> {
        return fetchData { service.searchForRecipeAsync(strIngredient) }
    }
}