package com.example.recipeapp.ui.activity.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.recipeapp.*
import com.example.recipeapp.data.model.food.Category
import com.example.recipeapp.ui.adapter.food.CategoryAdapter
import com.example.recipeapp.ui.fragment.food.CategoryFragment
import com.example.recipeapp.ui.fragment.food.RecipeFragment
import com.example.recipeapp.viewmodel.food.RandomRecipeViewModel
import kotlinx.android.synthetic.main.activity_food.*
import org.koin.android.viewmodel.ext.android.viewModel

class FoodActivity : AppCompatActivity(), CategoryAdapter.CategoryClickListener {
    private lateinit var fragmentManager: FragmentManager
    private var categoryFragment: CategoryFragment = CategoryFragment(this)
    private lateinit var backButton: ImageView
    private lateinit var header: TextView
    private val randomRecipeViewModel: RandomRecipeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        backButton = findViewById(R.id.back)
        header = findViewById(R.id.header)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, categoryFragment).commit()
        backButton.setOnClickListener { backButtonClicked() }
        random_generator.setOnClickListener {
            randomRecipeViewModel.getRandomRecipe()
            randomRecipeViewModel.getRandomRecipe().observe(this, Observer {
                val response = it.meals[0]
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("idMeal", response.idMeal)
                startActivity(intent)
            })

        }
    }

    override fun categoryItemClicked(task: Category) {
        fragmentManager.beginTransaction().replace(R.id.fragment, RecipeFragment(task)).commit()
        backButton.visibility = View.VISIBLE
        header.text = getString(R.string.recipe_header) + " " + task.strCategory
    }

    private fun backButtonClicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment, categoryFragment).commit()
        backButton.visibility = View.GONE
        header.text = getString(R.string.top_text)
    }

}