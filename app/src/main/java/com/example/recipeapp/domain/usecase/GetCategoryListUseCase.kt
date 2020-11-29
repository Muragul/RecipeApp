package com.example.recipeapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Category
import com.example.recipeapp.domain.repository.CategoryListRepository

class GetCategoryListUseCase(val categoryListRepository: CategoryListRepository) {
    fun getCategoryList(): LiveData<List<Category>> {
        return categoryListRepository.loadData()
    }

}