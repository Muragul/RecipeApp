package com.example.recipeapp.domain.repository.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.BarRecipe

interface BarRecipeListRepository {
    fun loadData(strCategory: String): LiveData<List<BarRecipe>>

}