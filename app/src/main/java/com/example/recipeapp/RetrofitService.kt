package com.example.recipeapp

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object RetrofitService {

    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    fun getPostApi(): PostApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PostApi::class.java)
    }

}

interface PostApi{

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