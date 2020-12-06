package com.example.recipeapp.domain.usecase.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.BarRecipe
import com.example.recipeapp.domain.repository.bar.BarRecipeListRepository

class GetBarRecipeListUseCase(private val barRecipeListRepository: BarRecipeListRepository) {
    fun getBarRecipeList(strCategory: String): LiveData<List<BarRecipe>>{
        return barRecipeListRepository.loadData(strCategory)
    }
}