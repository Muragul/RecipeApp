package com.example.recipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Recipe

interface RecipeListRepository {
    fun loadData(strCategory: String): LiveData<List<Recipe>>
}