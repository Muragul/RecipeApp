package com.example.recipeapp.domain.usecase.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.repository.food.RandomRecipeRepository

class GetRandomRecipeUseCase(private val randomRecipeRepository: RandomRecipeRepository) {
    fun getRandomRecipe(): LiveData<MealResponse> {
        return randomRecipeRepository.loadData()
    }
}