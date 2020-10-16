package com.example.recipeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.*
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.adapter.CategoryAdapter
import com.example.recipeapp.ui.fragment.CategoryFragment
import com.example.recipeapp.ui.fragment.RecipeFragment

class FoodActivity : AppCompatActivity(), CategoryAdapter.CategoryClickListener, RecipeFragment.BackButtonClicked {
    private lateinit var fragmentManager: FragmentManager
    private var categoryFragment: CategoryFragment = CategoryFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, categoryFragment).commit()
    }

    override fun categoryItemClicked(task: Category) {
        fragmentManager.beginTransaction().replace(R.id.fragment, RecipeFragment(this, task)).commit()
    }

    override fun backButtonClicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment, categoryFragment).commit()
    }

}