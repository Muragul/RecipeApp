package com.example.recipeapp.viewmodel.food

import org.junit.Test
import com.example.recipeapp.domain.repository.food.FakeCategoryListRepository
import com.example.recipeapp.domain.usecase.food.GetCategoryListUseCase
import com.example.recipeapp.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.recipeapp.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class FoodCategoryListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: FoodCategoryListViewModel
    private lateinit var fakeRepository: FakeCategoryListRepository
    private lateinit var fakeUseCase: GetCategoryListUseCase

    @Before
    fun setup() {
        fakeRepository = FakeCategoryListRepository()
        fakeUseCase = GetCategoryListUseCase(fakeRepository)
        viewModel = FoodCategoryListViewModel(fakeUseCase)
    }

    @Test
    fun getCategories_emptyList() {
        val items = fakeRepository.loadData().getOrAwaitValue()
        assertThat(items.isEmpty(), `is`(true))
    }
}