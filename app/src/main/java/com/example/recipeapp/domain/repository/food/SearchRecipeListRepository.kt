package com.example.recipeapp.domain.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Recipe

interface SearchRecipeListRepository {
    fun loadData(strIngredient: String): LiveData<List<Recipe>>
}