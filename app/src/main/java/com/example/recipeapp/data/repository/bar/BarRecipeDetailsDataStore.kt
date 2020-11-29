package com.example.recipeapp.data.repository.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.BarApiService
import com.example.recipeapp.data.model.bar.DrinkResponse
import com.example.recipeapp.domain.repository.BarRecipeDetailsRepository

class BarRecipeDetailsDataStore(barApiService: BarApiService) : BarRecipeDetailsRepository,
    BaseBarRecipeDetailsDataStore(barApiService) {
    override fun loadData(strDrink: String): LiveData<DrinkResponse> {
        return fetchData { service.getRecipeAsync(strDrink) }
    }
}