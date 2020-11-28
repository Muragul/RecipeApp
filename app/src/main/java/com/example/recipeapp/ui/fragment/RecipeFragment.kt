package com.example.recipeapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.databinding.RecipeListFragmentBinding
import com.example.recipeapp.ui.adapter.RecipeAdapter
import com.example.recipeapp.viewmodel.FoodRecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recipe_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeFragment(private val task: Category) :
    Fragment() {
    private lateinit var viewDataBinding: RecipeListFragmentBinding
    private lateinit var adapter: RecipeAdapter
    private val foodRecipeListViewModel: FoodRecipeListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = RecipeListFragmentBinding.inflate(inflater, container, false)
            .apply { lifecycleOwner = viewLifecycleOwner }
        viewDataBinding.viewmodel = foodRecipeListViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.getRecipeList(task.strCategory)
        setupAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewDataBinding.viewmodel?.getRecipeList(task.strCategory)
            ?.observe(viewLifecycleOwner, Observer {
                adapter.updateList(it)
            })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = RecipeAdapter()
            recycler_view.layoutManager = LinearLayoutManager(activity)
            recycler_view.adapter = adapter
        }

    }
}