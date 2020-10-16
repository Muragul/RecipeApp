package com.example.recipeapp.data.repository

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.domain.CategoryListRepository

class CategoryListDataStore(apiService: ApiService) : CategoryListRepository,
    BaseDataStore(apiService) {
    override fun loadData(): LiveData<List<Category>> {
        return fetchData { service.getPostListCoroutineAsync() }
    }
}
