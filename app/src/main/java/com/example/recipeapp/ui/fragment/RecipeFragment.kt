package com.example.recipeapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.ui.adapter.RecipeAdapter
import com.example.recipeapp.viewmodel.FoodRecipeListViewModel
import com.example.recipeapp.viewmodel.ViewModelProviderFactory

class RecipeFragment(val listener: BackButtonClicked, val task: Category) : Fragment() {
    private lateinit var header: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var recipeList: List<Recipe>
    private lateinit var foodRecipeListViewModel: FoodRecipeListViewModel
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
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val description: TextView = rootView.findViewById(R.id.description)
        description.text = task.strCategoryDescription
        val viewModelProviderFactory = ViewModelProviderFactory(activity as Context)
        foodRecipeListViewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(FoodRecipeListViewModel::class.java)
        foodRecipeListViewModel.getRecipeListByCategory(task.strCategory)
        foodRecipeListViewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is FoodRecipeListViewModel.State.ShowLoading -> {
                }
                is FoodRecipeListViewModel.State.HideLoading -> {
                }
                is FoodRecipeListViewModel.State.Result -> {
                    adapter.recipeList = result.list
                    adapter.notifyDataSetChanged()
                }
            }
        })
        header.text = header.text.toString() + task.strCategory
        header.setOnClickListener {
            if (description.visibility == View.VISIBLE)
                description.visibility = View.GONE
            else
                description.visibility = View.VISIBLE
        }
        initView()
        return rootView

    }

    private fun initView() {
        recipeList = ArrayList()
        adapter = RecipeAdapter(recipeList)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    interface BackButtonClicked{
        fun backButtonClicked()
    }
}