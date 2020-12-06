package com.example.recipeapp.domain.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Category

interface CategoryListRepository {
    fun loadData(): LiveData<List<Category>>
}