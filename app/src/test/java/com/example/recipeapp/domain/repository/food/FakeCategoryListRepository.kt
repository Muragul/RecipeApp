package com.example.recipeapp.domain.repository.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.model.food.Category

class FakeCategoryListRepository : CategoryListRepository {
    private val list = mutableListOf<Category>()
    private val liveData = MutableLiveData<List<Category>>(list)

    override fun loadData(): LiveData<List<Category>> {
        return liveData
    }

}