package com.example.recipeapp.domain.usecase.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.domain.repository.food.SearchRecipeListRepository

class SearchRecipeListUseCase(private val searchRecipeListRepository: SearchRecipeListRepository) {
    fun getRecipeList(strIngredient: String): LiveData<List<Recipe>> {
        return searchRecipeListRepository.loadData(strIngredient)
    }
}