package com.example.recipeapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.databinding.RecipeListFragmentBinding
import com.example.recipeapp.ui.adapter.RecipeAdapter
import com.example.recipeapp.viewmodel.FoodRecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeFragment(val listener: BackButtonClicked, val task: Category) : Fragment() {
    private lateinit var viewDataBinding: RecipeListFragmentBinding
    private lateinit var header: TextView
    private lateinit var adapter: RecipeAdapter
    private val foodRecipeListViewModel: FoodRecipeListViewModel by viewModel()
    private lateinit var backButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =
            inflater.inflate(R.layout.recipe_list_fragment, container, false) as ViewGroup
        header = rootView.findViewById(R.id.header)
        backButton = rootView.findViewById(R.id.back)
        backButton.setOnClickListener {
            listener.backButtonClicked()
        }
        val description: TextView = rootView.findViewById(R.id.description)
        description.text = task.strCategoryDescription
        header.text = header.text.toString() + task.strCategory
        header.setOnClickListener {
            if (description.visibility == View.VISIBLE)
                description.visibility = View.GONE
            else
                description.visibility = View.VISIBLE
        }
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
        viewDataBinding.viewmodel?.getRecipeList(task.strCategory)?.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = RecipeAdapter()
            val layoutManager = LinearLayoutManager(activity)
            recycler_view.layoutManager = layoutManager
            recycler_view.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    layoutManager.orientation
                )
            )
            recycler_view.adapter = adapter
        }

    }

    interface BackButtonClicked {
        fun backButtonClicked()
    }
}