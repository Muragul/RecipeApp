package com.example.recipeapp.domain.usecase.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.DrinkResponse
import com.example.recipeapp.domain.repository.bar.BarRecipeDetailsRepository

class GetBarRecipeDetailsUseCase(private val barRecipeDetailsRepository: BarRecipeDetailsRepository) {
    fun getBarRecipeDetails(strDrink: String): LiveData<DrinkResponse> {
        return barRecipeDetailsRepository.loadData(strDrink)
    }
}