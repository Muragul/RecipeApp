package com.example.recipeapp.ui.activity.bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.ui.adapter.bar.BarCategoryAdapter
import com.example.recipeapp.ui.fragment.bar.BarCategoryFragment
import com.example.recipeapp.ui.fragment.bar.BarRecipeFragment

class BarActivity : AppCompatActivity(), BarCategoryAdapter.BarCategoryClickListener {
    private lateinit var fragmentManager: FragmentManager
    private val barCategoryFragment: BarCategoryFragment = BarCategoryFragment(this)
    private lateinit var backButton: ImageView
    private lateinit var header: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar)
        backButton = findViewById(R.id.back)
        header = findViewById(R.id.header)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, barCategoryFragment).commit()
        backButton.setOnClickListener { backButtonClicked() }
    }

    override fun categoryItemClicked(task: BarCategory) {
        fragmentManager.beginTransaction().replace(R.id.fragment, BarRecipeFragment(task)).commit()
        backButton.visibility = View.VISIBLE
        header.text = task.strCategory
    }

    private fun backButtonClicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment, barCategoryFragment).commit()
        backButton.visibility = View.GONE
        header.text = getString(R.string.bar_top_text)
    }
}