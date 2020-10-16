package com.example.recipeapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.api.ApiService
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.data.model.CategoryResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataStore(@PublishedApi internal val service: ApiService) {

    abstract fun loadData(): LiveData<List<Category>>

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<CategoryResponse>>): LiveData<List<Category>> {
        val result = MutableLiveData<List<Category>>()
        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()?.categories
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