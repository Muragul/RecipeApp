package com.example.recipeapp.viewmodel.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.DrinkResponse
import com.example.recipeapp.domain.usecase.bar.GetBarRecipeDetailsUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class BarRecipeDetailsViewModel(private val getBarRecipeDetailsUseCase: GetBarRecipeDetailsUseCase) :
    BaseViewModel() {
    fun getBarRecipeDetails(strDrink: String): LiveData<DrinkResponse> {
        return getBarRecipeDetailsUseCase.getBarRecipeDetails(strDrink)
    }
}