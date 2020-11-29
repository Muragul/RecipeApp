package com.example.recipeapp.ui.fragment.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.databinding.BarCategoriesFragmentBinding
import com.example.recipeapp.ui.adapter.bar.BarCategoryAdapter
import com.example.recipeapp.viewmodel.bar.BarCategoryListViewModel
import kotlinx.android.synthetic.main.categories_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager


class BarCategoryFragment(val listener: BarCategoryAdapter.BarCategoryClickListener) : Fragment() {
    private lateinit var viewDataBinding: BarCategoriesFragmentBinding
    private lateinit var adapter: BarCategoryAdapter
    private val barCategoryListViewModel: BarCategoryListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = BarCategoriesFragmentBinding.inflate(inflater, container, false)
            .apply { lifecycleOwner = viewLifecycleOwner }
        viewDataBinding.viewmodel = barCategoryListViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.getBarCategories()
        setupAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewDataBinding.viewmodel?.getBarCategories()?.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = BarCategoryAdapter(viewDataBinding.viewmodel!!, listener)
            recycler_view.layoutManager = LinearLayoutManager(activity)
            recycler_view.adapter = adapter
        }

    }


}