package com.example.recipeapp.ui.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.ui.adapter.user.FoodRecipeAdapter
import kotlinx.android.synthetic.main.food_fragment.view.*

class FoodFragment(private val listener: FoodRecipeAdapter.FoodRecipeClickListener) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater
            .inflate(
                R.layout.food_fragment,
                container, false
            ) as ViewGroup
        rootView.recycler_view.layoutManager = GridLayoutManager(context, 3)
        rootView.recycler_view.adapter = FoodRecipeAdapter(listener)
        return rootView
    }

}