package com.example.recipeapp.viewmodel.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.usecase.GetRandomRecipeUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class RandomRecipeViewModel(private val getRandomRecipeUseCase: GetRandomRecipeUseCase) :
    BaseViewModel() {
    fun getRandomRecipe(): LiveData<MealResponse> {
        return getRandomRecipeUseCase.getRandomRecipe()
    }
}