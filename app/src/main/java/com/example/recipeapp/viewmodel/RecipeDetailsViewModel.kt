package com.example.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.model.MealResponse
import com.example.recipeapp.domain.usecase.GetRecipeDetailsUseCase

class RecipeDetailsViewModel(private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase) :
    BaseViewModel() {

    fun getRecipeDetails(strMeal: String): LiveData<MealResponse> {
        return getRecipeDetailsUseCase.getRecipeDetails(strMeal)
    }
}