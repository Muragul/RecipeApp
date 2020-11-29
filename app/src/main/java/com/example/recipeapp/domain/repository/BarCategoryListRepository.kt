package com.example.recipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.BarCategory

interface BarCategoryListRepository {
    fun loadData(): LiveData<List<BarCategory>>

}