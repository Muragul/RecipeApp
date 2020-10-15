package com.example.recipeapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.adapter.CategoryAdapter
import com.example.recipeapp.viewmodel.FoodCategoryListViewModel
import com.example.recipeapp.viewmodel.ViewModelProviderFactory

class CategoryFragment(val listener: CategoryAdapter.CategoryClickListener) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private lateinit var categoriesList: List<Category>
    private lateinit var foodCategoryListViewModel: FoodCategoryListViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater
            .inflate(R.layout.categories_fragment, container, false)
                as ViewGroup
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val viewModelProviderFactory = ViewModelProviderFactory(activity as Context)
        foodCategoryListViewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(FoodCategoryListViewModel::class.java)

        foodCategoryListViewModel.getCategories()
        foodCategoryListViewModel.liveData.observe(this, Observer { result ->
            when (result) {
                is FoodCategoryListViewModel.State.ShowLoading -> {
                }
                is FoodCategoryListViewModel.State.HideLoading -> {
                }
                is FoodCategoryListViewModel.State.Result -> {
                    adapter.categoryList = result.list
                    adapter.notifyDataSetChanged()
                }

            }
        })
        initView()
        return rootView
    }

    private fun initView() {
        categoriesList = ArrayList()
        adapter = CategoryAdapter(activity as Context, categoriesList, listener)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}