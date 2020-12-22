package com.example.recipeapp.domain.repository.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.model.food.MealResponse

class FakeRecipeDetailsRepository : RecipeDetailsRepository {
    private val liveData = MutableLiveData<MealResponse>()

    override fun loadData(strMeal: String): LiveData<MealResponse> {
        return liveData
    }
}