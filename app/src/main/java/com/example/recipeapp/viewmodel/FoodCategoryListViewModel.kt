package com.example.recipeapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.api.ApiClient
import com.example.recipeapp.data.model.Category
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class FoodCategoryListViewModel(context: Context) : ViewModel(), CoroutineScope {
    private val job = Job()
    val liveData = MutableLiveData<State>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getCategories() {
        launch {
            liveData.value = State.ShowLoading
            try {
                val response = ApiClient.getApiService().getPostListCoroutine()
                if (response.isSuccessful) {
                    val list = response.body()?.categories
                    liveData.value = State.Result(list as List<Category>)
                }
            } catch (e: Exception) {
            }
            liveData.value = State.HideLoading
        }

    }

    sealed class State {
        object ShowLoading : State()
        object HideLoading : State()
        data class Result(val list: List<Category>) : State()
    }

}