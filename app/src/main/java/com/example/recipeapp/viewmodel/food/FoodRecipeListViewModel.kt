package com.example.recipeapp.viewmodel.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.domain.usecase.GetRecipeListUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class FoodRecipeListViewModel(private val getRecipeListUseCase: GetRecipeListUseCase) :
    BaseViewModel() {

    fun getRecipeList(strCategory: String): LiveData<List<Recipe>> {
        return getRecipeListUseCase.getRecipeList(strCategory)
    }

}