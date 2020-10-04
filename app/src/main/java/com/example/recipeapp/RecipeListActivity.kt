package com.example.recipeapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class RecipeListActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var header: TextView
    private val job = Job()
    private lateinit var strCategory: String
    private lateinit var recyclerView: RecyclerView
    private var adapter: RecipeAdapter? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        header = findViewById(R.id.header)
        strCategory = intent.getStringExtra("strCategory")
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        header.text = "Category: " + strCategory
        val description: TextView = findViewById(R.id.description)
        description.text = intent.getStringExtra("strCategoryDescription")

        adapter = RecipeAdapter()
        recyclerView.adapter = adapter

        header.setOnClickListener {
            if (description.visibility == View.VISIBLE)
                description.visibility = View.GONE
            else
                description.visibility = View.VISIBLE
        }

        getPostListByCategory(strCategory)

    }

    private fun getPostListByCategory(strCategory: String) {
        launch {
            val response =
                RetrofitService.getPostApi().getPostListByCategory(strCategory)
            if (response.isSuccessful) {
                adapter?.list = response.body()?.meals
                adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this@RecipeListActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}