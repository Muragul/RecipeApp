package com.example.recipeapp.data.repository.food

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.food.Category
import com.example.recipeapp.domain.repository.CategoryListRepository

class CategoryListDataStore(apiService: ApiService) : CategoryListRepository,
    BaseDataStore(apiService) {
    override fun loadData(): LiveData<List<Category>> {
        return fetchData { service.getPostListCoroutineAsync() }
    }
}
