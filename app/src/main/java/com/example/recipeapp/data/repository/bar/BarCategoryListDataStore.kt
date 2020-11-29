package com.example.recipeapp.data.repository.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.api.BarApiService
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.domain.repository.BarCategoryListRepository

class BarCategoryListDataStore(barApiService: BarApiService) : BarCategoryListRepository,
    BaseBarDataStore(barApiService) {
    override fun loadData(): LiveData<List<BarCategory>> {
        return fetchData { service.getPostListCoroutineAsync("list") }
    }
}