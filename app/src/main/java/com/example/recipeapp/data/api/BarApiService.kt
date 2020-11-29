package com.example.recipeapp.data.api

import com.example.recipeapp.data.model.bar.BarCategoryResponse
import com.example.recipeapp.data.model.bar.BarRecipeResponse
import com.example.recipeapp.data.model.bar.DrinkResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BarApiService {

    @GET("list.php")
    fun getPostListCoroutineAsync(
        @Query("c") list: String
    ): Deferred<Response<BarCategoryResponse>>

    @GET("filter.php")
    fun getPostListByCategoryAsync(
        @Query("c") strCategory: String
    ): Deferred<Response<BarRecipeResponse>>

    @GET("lookup.php")
    fun getRecipeAsync(
        @Query("i") idDrink: String
    ): Deferred<Response<DrinkResponse>>
}