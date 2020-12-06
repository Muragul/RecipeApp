package com.example.recipeapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.repository.RandomRecipeRepository

class GetRandomRecipeUseCase(private val randomRecipeRepository: RandomRecipeRepository) {
    fun getRandomRecipe(): LiveData<MealResponse> {
        return randomRecipeRepository.loadData()
    }
}