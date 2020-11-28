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
import com.example.recipeapp.databinding.CategoriesFragmentBinding
import com.example.recipeapp.ui.adapter.CategoryAdapter
import com.example.recipeapp.viewmodel.FoodCategoryListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.categories_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment(val listener: CategoryAdapter.CategoryClickListener) : Fragment() {
    private lateinit var viewDataBinding: CategoriesFragmentBinding
    private lateinit var adapter: CategoryAdapter
    private val foodCategoryListViewModel: FoodCategoryListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = CategoriesFragmentBinding.inflate(inflater, container, false)
            .apply { lifecycleOwner = viewLifecycleOwner }
        viewDataBinding.viewmodel = foodCategoryListViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.getCategories()
        setupAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewDataBinding.viewmodel?.getCategories()?.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = CategoryAdapter(viewDataBinding.viewmodel!!, listener)
            recycler_view.layoutManager = GridLayoutManager(activity, 3)
            recycler_view.adapter = adapter
        }

    }

}