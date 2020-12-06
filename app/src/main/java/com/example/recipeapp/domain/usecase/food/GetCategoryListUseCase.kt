package com.example.recipeapp.domain.usecase.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Category
import com.example.recipeapp.domain.repository.food.CategoryListRepository

class GetCategoryListUseCase(private val categoryListRepository: CategoryListRepository) {
    fun getCategoryList(): LiveData<List<Category>> {
        return categoryListRepository.loadData()
    }

}