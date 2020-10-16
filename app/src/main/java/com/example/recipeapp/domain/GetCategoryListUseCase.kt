package com.example.recipeapp.domain

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Category

class GetCategoryListUseCase(val categoryListRepository: CategoryListRepository) {
    fun getCategoryList(): LiveData<List<Category>> {
        return categoryListRepository.loadData()
    }

}