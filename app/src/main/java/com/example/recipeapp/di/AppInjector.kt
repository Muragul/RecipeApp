package com.example.recipeapp.di

import android.content.SharedPreferences
import com.example.recipeapp.data.api.ApiClient.getApiService
import com.example.recipeapp.data.repository.CategoryListDataStore
import com.example.recipeapp.data.repository.RecipeListDataStore
import com.example.recipeapp.domain.GetCategoryListUseCase
import com.example.recipeapp.domain.GetRecipeListUseCase
import com.example.recipeapp.viewmodel.FoodCategoryListViewModel
import com.example.recipeapp.viewmodel.FoodRecipeListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val networkModule = module {
    single { getApiService() }
}

val viewModelModule = module {
    viewModel { FoodCategoryListViewModel(get()) }
    viewModel { FoodRecipeListViewModel(get()) }
}

val useCaseModule = module {
    single { GetCategoryListUseCase(get<CategoryListDataStore>()) }
    single { GetRecipeListUseCase(get<RecipeListDataStore>()) }
}

val repositoryModule = module {
    single { CategoryListDataStore(get()) }
    single { RecipeListDataStore(get()) }
}

val sharedPrefModule = module {
    single {
        androidApplication().getSharedPreferences("default", android.content.Context.MODE_PRIVATE)
    }

    single<SharedPreferences.Editor> {
        androidApplication().getSharedPreferences("default", android.content.Context.MODE_PRIVATE)
            .edit()
    }
}