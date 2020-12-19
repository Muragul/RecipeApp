package com.example.recipeapp.ui.activity.food

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.ui.activity.GraphActivity
import com.example.recipeapp.ui.fragment.food.SearchFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        fragmentManager = supportFragmentManager
        back.setOnClickListener {
            onBackPressed()
        }
        val popupMenu = PopupMenu(this, open_menu)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment, SearchFragment("avocado"))
                        .commit()
                    preview_text.text = ""
                    true
                }
                R.id.menu2 -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment, SearchFragment("vanilla"))
                        .commit()
                    preview_text.text = ""
                    true
                }
                R.id.menu3 -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment, SearchFragment("basil"))
                        .commit()
                    preview_text.text = ""
                    true
                }
                R.id.menu4 -> {
                    val intent = Intent(this, GraphActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        open_menu.setOnClickListener {
            popupMenu.show()
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search_view.clearFocus()
                fragmentManager.beginTransaction().replace(R.id.fragment, SearchFragment(query))
                    .commit()
                preview_text.text = ""
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }
}

