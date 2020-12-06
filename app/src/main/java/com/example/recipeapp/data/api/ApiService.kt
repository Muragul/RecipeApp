package com.example.recipeapp.data.api

import com.example.recipeapp.data.model.food.CategoryResponse
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.data.model.food.RecipeResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    fun getPostListCoroutineAsync(): Deferred<Response<CategoryResponse>>

    @GET("filter.php")
    fun getPostListByCategoryAsync(
        @Query("c") strCategory: String
    ): Deferred<Response<RecipeResponse>>

    @GET("lookup.php")
    fun getRecipeAsync(
        @Query("i") idMeal: String
    ): Deferred<Response<MealResponse>>

    @GET("random.php")
    fun getRandomRecipeAsync(): Deferred<Response<MealResponse>>

    @GET("filter.php")
    fun searchForRecipeAsync(@Query("i") ingredient: String): Deferred<Response<RecipeResponse>>
}