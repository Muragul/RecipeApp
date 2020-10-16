package com.example.recipeapp.domain

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Recipe

class GetRecipeListUseCase(val recipeListRepository: RecipeListRepository) {
    fun getRecipeList(strCategory: String): LiveData<List<Recipe>> {
        return recipeListRepository.loadData(strCategory)
    }

}