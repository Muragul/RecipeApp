package com.example.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.domain.GetCategoryListUseCase

class FoodCategoryListViewModel(private val getCategoryListUseCase: GetCategoryListUseCase) :
    BaseViewModel() {

    fun getCategories(): LiveData<List<Category>> {
        return getCategoryListUseCase.getCategoryList()
    }
}