package com.example.recipeapp.viewmodel.bar

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.bar.BarRecipe
import com.example.recipeapp.domain.usecase.GetBarRecipeListUseCase
import com.example.recipeapp.viewmodel.BaseViewModel

class BarRecipeListViewModel(private val getBarRecipeListUseCase: GetBarRecipeListUseCase) :
    BaseViewModel() {
    fun getBarRecipeList(strCategory: String): LiveData<List<BarRecipe>> {
        return getBarRecipeListUseCase.getBarRecipeList(strCategory)
    }
}