package com.example.recipeapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.data.model.MealResponse
import com.example.recipeapp.domain.repository.RecipeDetailsRepository

class GetRecipeDetailsUseCase(private val recipeDetailsRepository: RecipeDetailsRepository) {
    fun getRecipeDetails(strMeal: String): LiveData<MealResponse>{
        return recipeDetailsRepository.loadData(strMeal)
    }
}