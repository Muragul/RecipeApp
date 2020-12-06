package com.example.recipeapp.ui.activity.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.ui.fragment.food.SearchFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        fragmentManager = supportFragmentManager

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search_view.clearFocus()
                fragmentManager.beginTransaction().replace(R.id.fragment, SearchFragment(query)).commit()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }
}

