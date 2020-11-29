package com.example.recipeapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.DrinkResponse
import com.example.recipeapp.domain.repository.BarRecipeDetailsRepository

class GetBarRecipeDetailsUseCase(private val barRecipeDetailsRepository: BarRecipeDetailsRepository) {
    fun getBarRecipeDetails(strDrink: String): LiveData<DrinkResponse> {
        return barRecipeDetailsRepository.loadData(strDrink)
    }
}