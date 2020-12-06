package com.example.recipeapp.viewmodel.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.domain.usecase.food.SearchRecipeListUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class SearchViewModel(private val searchRecipeListUseCase: SearchRecipeListUseCase) :
    BaseViewModel() {
    fun getRecipeList(strIngredient: String): LiveData<List<Recipe>> {
        return searchRecipeListUseCase.getRecipeList(strIngredient)
    }
}