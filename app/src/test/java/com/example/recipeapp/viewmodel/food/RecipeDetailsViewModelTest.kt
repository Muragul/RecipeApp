package com.example.recipeapp.viewmodel.food

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.recipeapp.MainCoroutineRule
import com.example.recipeapp.domain.repository.food.FakeRecipeDetailsRepository
import com.example.recipeapp.domain.usecase.food.GetRecipeDetailsUseCase
import com.example.recipeapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.lang.Exception

@ExperimentalCoroutinesApi
class RecipeDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RecipeDetailsViewModel
    private lateinit var fakeRepository: FakeRecipeDetailsRepository
    private lateinit var fakeUseCase: GetRecipeDetailsUseCase

    @Before
    fun setup() {
        fakeRepository = FakeRecipeDetailsRepository()
        fakeUseCase = GetRecipeDetailsUseCase(fakeRepository)
        viewModel = RecipeDetailsViewModel(fakeUseCase)
    }

    @Test
    fun getRecipeDetails() {
        val result = try {
            val item = fakeRepository.loadData("").getOrAwaitValue()
            true
        } catch (e: Exception) {
            false
        }
        assertThat(result, `is`(false))
    }
}