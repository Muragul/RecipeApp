package com.example.recipeapp.ui.fragment.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.databinding.FragmentSearchBinding
import com.example.recipeapp.ui.adapter.food.SearchAdapter
import com.example.recipeapp.viewmodel.food.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment(private val strIngredient: String) : Fragment() {
    private lateinit var viewDataBinding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentSearchBinding.inflate(inflater, container, false)
            .apply { lifecycleOwner = viewLifecycleOwner }
        viewDataBinding.viewmodel = searchViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.getRecipeList(strIngredient)
        setupAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewDataBinding.viewmodel?.getRecipeList(strIngredient)
            ?.observe(viewLifecycleOwner, Observer {
                adapter.updateList(it)
            })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = SearchAdapter()
            recycler_view.layoutManager = LinearLayoutManager(activity)
            recycler_view.adapter = adapter
        }

    }

}