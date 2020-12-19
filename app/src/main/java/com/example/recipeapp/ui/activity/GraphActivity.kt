package com.example.recipeapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.ui.fragment.statistics.GraphFragment
import com.example.recipeapp.ui.fragment.statistics.HealthyFragment
import com.example.recipeapp.ui.fragment.statistics.SportFragment
import kotlinx.android.synthetic.main.activity_graph.*


class GraphActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        back.setOnClickListener { onBackPressed() }
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.graph_fragment, GraphFragment()).commit()
        fragmentManager.beginTransaction().replace(R.id.healthy_fragment, HealthyFragment())
            .commit()
        fragmentManager.beginTransaction().replace(R.id.sport_fragment, SportFragment()).commit()

    }

}