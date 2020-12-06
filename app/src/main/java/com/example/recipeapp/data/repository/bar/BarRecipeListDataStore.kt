package com.example.recipeapp.data.repository.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.BarApiService
import com.example.recipeapp.data.model.bar.BarRecipe
import com.example.recipeapp.domain.repository.bar.BarRecipeListRepository

class BarRecipeListDataStore(barApiService: BarApiService) : BarRecipeListRepository,
    BaseBarRecipeDataStore(barApiService) {
    override fun loadData(strCategory: String): LiveData<List<BarRecipe>> {
        return fetchData { service.getPostListByCategoryAsync(strCategory) }
    }
}