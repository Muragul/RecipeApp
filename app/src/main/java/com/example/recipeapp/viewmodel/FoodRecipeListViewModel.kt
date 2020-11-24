package com.example.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.domain.usecase.GetRecipeListUseCase

class FoodRecipeListViewModel(private val getRecipeListUseCase: GetRecipeListUseCase) :
    BaseViewModel() {

    fun getRecipeList(strCategory: String): LiveData<List<Recipe>> {
        return getRecipeListUseCase.getRecipeList(strCategory)
    }

}