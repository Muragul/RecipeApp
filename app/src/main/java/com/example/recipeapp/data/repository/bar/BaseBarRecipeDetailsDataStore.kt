package com.example.recipeapp.data.repository.bar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.data.api.BarApiService
import com.example.recipeapp.data.model.bar.DrinkResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseBarRecipeDetailsDataStore(@PublishedApi internal val service: BarApiService) {
    abstract fun loadData(strDrink: String): LiveData<DrinkResponse>

    inline fun fetchData(crossinline call: (BarApiService) -> Deferred<Response<DrinkResponse>>): LiveData<DrinkResponse> {
        val result = MutableLiveData<DrinkResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        result.value = response.body()
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