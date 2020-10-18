package com.example.recipeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.*
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.adapter.CategoryAdapter
import com.example.recipeapp.ui.fragment.CategoryFragment
import com.example.recipeapp.ui.fragment.RecipeFragment

class FoodActivity : AppCompatActivity(), CategoryAdapter.CategoryClickListener {
    private lateinit var fragmentManager: FragmentManager
    private var categoryFragment: CategoryFragment = CategoryFragment(this)
    private lateinit var backButton: ImageView
    private lateinit var header: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        backButton = findViewById(R.id.back)
        header = findViewById(R.id.header)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, categoryFragment).commit()
        backButton.setOnClickListener { backButtonClicked() }
    }

    override fun categoryItemClicked(task: Category) {
        fragmentManager.beginTransaction().replace(R.id.fragment, RecipeFragment(task)).commit()
        backButton.visibility = View.VISIBLE
        header.text = getString(R.string.recipe_header) + " " + task.strCategory ?: null
    }

    private fun backButtonClicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment, categoryFragment).commit()
        backButton.visibility = View.GONE
        header.text = getString(R.string.top_text)
    }

}