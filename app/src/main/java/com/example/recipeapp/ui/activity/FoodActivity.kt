package com.example.recipeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.*
import com.example.recipeapp.ui.adapter.CategoryAdapter
import com.example.recipeapp.ui.fragment.CategoryFragment
import com.example.recipeapp.ui.fragment.RecipeFragment

class FoodActivity : AppCompatActivity(), CategoryAdapter.CategoryClickListener {
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, CategoryFragment(this)).commit()
    }

    override fun categoryItemClicked(strCategory: String) {
        fragmentManager.beginTransaction().replace(R.id.fragment, RecipeFragment(strCategory)).commit()
    }

}