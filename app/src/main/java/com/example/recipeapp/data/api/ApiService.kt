package com.example.recipeapp.data.api

import com.example.recipeapp.data.model.CategoryResponse
import com.example.recipeapp.data.model.MealResponse
import com.example.recipeapp.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getPostListCoroutine(): Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getPostListByCategory(
        @Query("c")strCategory: String
    ): Response<RecipeResponse>

    @GET("lookup.php")
    suspend fun getRecipe(
        @Query("i")idMeal: String
    ): Response<MealResponse>
}