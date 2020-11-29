package com.example.recipeapp.data.repository.bar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.api.BarApiService
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.data.model.bar.BarCategoryResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseBarDataStore(@PublishedApi internal val service: BarApiService) {

    abstract fun loadData(): LiveData<List<BarCategory>>

    inline fun fetchData(crossinline call: (BarApiService) -> Deferred<Response<BarCategoryResponse>>): LiveData<List<BarCategory>> {
        val result = MutableLiveData<List<BarCategory>>()
        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()?.barCategories
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