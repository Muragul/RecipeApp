package com.example.recipeapp.domain.repository.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.model.food.Recipe

class FakeRecipeListRepository : RecipeListRepository {
    private val list = mutableListOf<Recipe>()
    private val liveData = MutableLiveData<List<Recipe>>(list)

    override fun loadData(strCategory: String): LiveData<List<Recipe>> {
        return liveData
    }
}