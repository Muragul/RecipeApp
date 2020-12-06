package com.example.recipeapp.domain.usecase.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.domain.repository.food.RecipeDetailsRepository

class GetRecipeDetailsUseCase(private val recipeDetailsRepository: RecipeDetailsRepository) {
    fun getRecipeDetails(strMeal: String): LiveData<MealResponse>{
        return recipeDetailsRepository.loadData(strMeal)
    }
}