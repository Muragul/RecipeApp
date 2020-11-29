package com.example.recipeapp.di

import android.content.SharedPreferences
import com.example.recipeapp.data.api.ApiClient.getApiService
import com.example.recipeapp.data.api.BarApiClient.getBarApiService
import com.example.recipeapp.data.repository.bar.BarCategoryListDataStore
import com.example.recipeapp.data.repository.bar.BarRecipeDetailsDataStore
import com.example.recipeapp.data.repository.bar.BarRecipeListDataStore
import com.example.recipeapp.data.repository.food.CategoryListDataStore
import com.example.recipeapp.data.repository.food.RecipeDetailsDataStore
import com.example.recipeapp.data.repository.food.RecipeListDataStore
import com.example.recipeapp.domain.usecase.*
import com.example.recipeapp.viewmodel.bar.BarCategoryListViewModel
import com.example.recipeapp.viewmodel.bar.BarRecipeDetailsViewModel
import com.example.recipeapp.viewmodel.bar.BarRecipeListViewModel
import com.example.recipeapp.viewmodel.food.FoodCategoryListViewModel
import com.example.recipeapp.viewmodel.food.FoodRecipeListViewModel
import com.example.recipeapp.viewmodel.food.RecipeDetailsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val networkModule = module {
    single { getApiService() }
    single { getBarApiService() }
}

val viewModelModule = module {
    viewModel { FoodCategoryListViewModel(get()) }
    viewModel { FoodRecipeListViewModel(get()) }
    viewModel { RecipeDetailsViewModel(get()) }
    viewModel { BarCategoryListViewModel(get()) }
    viewModel { BarRecipeListViewModel(get()) }
    viewModel { BarRecipeDetailsViewModel(get()) }
}

val useCaseModule = module {
    single { GetCategoryListUseCase(get<CategoryListDataStore>()) }
    single { GetRecipeListUseCase(get<RecipeListDataStore>()) }
    single { GetRecipeDetailsUseCase(get<RecipeDetailsDataStore>()) }
    single { GetBarCategoryListUseCase(get<BarCategoryListDataStore>()) }
    single { GetBarRecipeListUseCase(get<BarRecipeListDataStore>()) }
    single { GetBarRecipeDetailsUseCase(get<BarRecipeDetailsDataStore>()) }
}

val repositoryModule = module {
    single { CategoryListDataStore(get()) }
    single { RecipeListDataStore(get()) }
    single { RecipeDetailsDataStore(get()) }
    single { BarCategoryListDataStore(get()) }
    single { BarRecipeListDataStore(get()) }
    single { BarRecipeDetailsDataStore(get()) }
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