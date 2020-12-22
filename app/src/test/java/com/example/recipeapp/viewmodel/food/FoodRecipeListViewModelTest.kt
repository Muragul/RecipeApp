package com.example.recipeapp.viewmodel.food

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.recipeapp.MainCoroutineRule
import com.example.recipeapp.domain.repository.food.FakeCategoryListRepository
import com.example.recipeapp.domain.repository.food.FakeRecipeListRepository
import com.example.recipeapp.domain.usecase.food.GetCategoryListUseCase
import com.example.recipeapp.domain.usecase.food.GetRecipeListUseCase
import com.example.recipeapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class FoodRecipeListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: FoodRecipeListViewModel
    private lateinit var fakeRepository: FakeRecipeListRepository
    private lateinit var fakeUseCase: GetRecipeListUseCase

    @Before
    fun setup() {
        fakeRepository = FakeRecipeListRepository()
        fakeUseCase = GetRecipeListUseCase(fakeRepository)
        viewModel = FoodRecipeListViewModel(fakeUseCase)
    }

    @Test
    fun getRecipeList_emptyList() {
        val items = fakeRepository.loadData("").getOrAwaitValue()
        MatcherAssert.assertThat(items.isEmpty(), CoreMatchers.`is`(true))
    }
}