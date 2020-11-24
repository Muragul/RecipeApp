package com.example.recipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Category

interface CategoryListRepository {
    fun loadData(): LiveData<List<Category>>
}