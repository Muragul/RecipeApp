package com.example.recipeapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.api.ApiClient
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.domain.GetRecipeListUseCase
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class FoodRecipeListViewModel(private val getRecipeListUseCase: GetRecipeListUseCase) :
    BaseViewModel() {

    fun getRecipeList(strCategory: String): LiveData<List<Recipe>> {
        return getRecipeListUseCase.getRecipeList(strCategory)
    }

}