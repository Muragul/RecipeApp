package com.example.recipeapp.viewmodel.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.domain.usecase.bar.GetBarCategoryListUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class BarCategoryListViewModel(private val getBarCategoryListUseCase: GetBarCategoryListUseCase) :
    BaseViewModel() {
    fun getBarCategories(): LiveData<List<BarCategory>> {
        return getBarCategoryListUseCase.getBarCategoryList()
    }
}