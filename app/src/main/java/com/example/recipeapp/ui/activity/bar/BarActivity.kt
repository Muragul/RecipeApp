package com.example.recipeapp.ui.activity.bar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.ui.adapter.bar.BarCategoryAdapter
import com.example.recipeapp.ui.fragment.bar.BarCategoryFragment
import com.example.recipeapp.ui.fragment.bar.BarRecipeFragment
import kotlinx.android.synthetic.main.activity_bar.*

class BarActivity : AppCompatActivity(), BarCategoryAdapter.BarCategoryClickListener {
    private lateinit var fragmentManager: FragmentManager
    private val barCategoryFragment: BarCategoryFragment = BarCategoryFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, barCategoryFragment).commit()
        back.setOnClickListener { backButtonClicked() }
        view_map.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    override fun categoryItemClicked(task: BarCategory) {
        fragmentManager.beginTransaction().replace(R.id.fragment, BarRecipeFragment(task)).commit()
        back.visibility = View.VISIBLE
        header.text = task.strCategory
    }

    private fun backButtonClicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment, barCategoryFragment).commit()
        back.visibility = View.GONE
        header.text = getString(R.string.bar_top_text)
    }
}