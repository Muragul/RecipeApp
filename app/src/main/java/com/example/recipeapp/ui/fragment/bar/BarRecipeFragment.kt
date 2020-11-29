package com.example.recipeapp.ui.fragment.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.databinding.BarRecipeListFragmentBinding
import com.example.recipeapp.databinding.RecipeListFragmentBinding
import com.example.recipeapp.ui.adapter.bar.BarRecipeAdapter
import com.example.recipeapp.ui.adapter.food.RecipeAdapter
import com.example.recipeapp.viewmodel.bar.BarRecipeListViewModel
import kotlinx.android.synthetic.main.bar_recipe_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class BarRecipeFragment(private val task: BarCategory) : Fragment() {
    private lateinit var viewDataBinding: BarRecipeListFragmentBinding
    private lateinit var adapter: BarRecipeAdapter
    private val barRecipeListViewModel: BarRecipeListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = BarRecipeListFragmentBinding.inflate(inflater, container, false)
            .apply { lifecycleOwner = viewLifecycleOwner }
        viewDataBinding.viewmodel = barRecipeListViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.getBarRecipeList(task.strCategory)
        setupAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewDataBinding.viewmodel?.getBarRecipeList(task.strCategory)
            ?.observe(viewLifecycleOwner, Observer {
                adapter.updateList(it)
            })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = BarRecipeAdapter()
            recycler_view.layoutManager = LinearLayoutManager(activity)
            recycler_view.adapter = adapter
        }

    }
}