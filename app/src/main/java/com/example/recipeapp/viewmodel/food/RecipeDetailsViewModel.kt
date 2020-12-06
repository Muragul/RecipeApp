package com.example.recipeapp.viewmodel.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.usecase.food.GetRecipeDetailsUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class RecipeDetailsViewModel(private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase) :
    BaseViewModel() {

    fun getRecipeDetails(strMeal: String): LiveData<MealResponse> {
        return getRecipeDetailsUseCase.getRecipeDetails(strMeal)
    }
}