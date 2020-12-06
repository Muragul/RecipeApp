package com.example.recipeapp.domain.usecase.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.domain.repository.bar.BarCategoryListRepository

class GetBarCategoryListUseCase(private val barCategoryListRepository: BarCategoryListRepository) {
    fun getBarCategoryList(): LiveData<List<BarCategory>> {
        return barCategoryListRepository.loadData()
    }
}