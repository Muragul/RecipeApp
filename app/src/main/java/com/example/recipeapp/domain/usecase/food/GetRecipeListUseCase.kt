package com.example.recipeapp.domain.usecase.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.domain.repository.food.RecipeListRepository

class GetRecipeListUseCase(private val recipeListRepository: RecipeListRepository) {
    fun getRecipeList(strCategory: String): LiveData<List<Recipe>> {
        return recipeListRepository.loadData(strCategory)
    }

}