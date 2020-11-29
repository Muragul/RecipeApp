package com.example.recipeapp.data.repository.bar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.api.BarApiService
import com.example.recipeapp.data.model.bar.BarRecipe
import com.example.recipeapp.data.model.bar.BarRecipeResponse
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.data.model.food.RecipeResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseBarRecipeDataStore(@PublishedApi internal val service: BarApiService) {
    abstract fun loadData(strCategory: String): LiveData<List<BarRecipe>>

    inline fun fetchData(crossinline call: (BarApiService) -> Deferred<Response<BarRecipeResponse>>): LiveData<List<BarRecipe>> {
        val result = MutableLiveData<List<BarRecipe>>()
        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()?.drinks
                    } else {
                        Timber.d("Error occurred with code ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("Error: ${e.message()}")
                } catch (e: Throwable) {
                    Timber.d("Error: ${e.message}")
                }
            }
        }
        return result
    }

}