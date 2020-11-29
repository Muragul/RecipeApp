package com.example.recipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.DrinkResponse

interface BarRecipeDetailsRepository {
    fun loadData(strDrink: String): LiveData<DrinkResponse>

}