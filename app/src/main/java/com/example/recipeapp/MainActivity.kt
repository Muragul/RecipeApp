package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    lateinit var text: TextView
    private lateinit var recyclerView: RecyclerView
    private var adapter: CategoryAdapter? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CategoryAdapter()
        recyclerView.adapter = adapter

        text = findViewById(R.id.header)
        getPostsCoroutine()

    }

    private fun getPostsCoroutine() {
        launch {
            val response = RetrofitService.getPostApi().getPostListCoroutine()
            if (response.isSuccessful) {
                val list = response.body()?.categories
                adapter?.list = list
                adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        }

    }

}
