package com.example.recipeapp.viewmodel.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.food.Category
import com.example.recipeapp.domain.usecase.GetCategoryListUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class FoodCategoryListViewModel(private val getCategoryListUseCase: GetCategoryListUseCase) :
    BaseViewModel() {

    fun getCategories(): LiveData<List<Category>> {
        return getCategoryListUseCase.getCategoryList()
    }
}