package com.example.recipeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.api.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()
    private lateinit var title: TextView
    private lateinit var image: ImageView
    private lateinit var instructions: TextView
    private lateinit var backButton: ImageView
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id: String = intent.getStringExtra("idMeal")!!
        backButton = findViewById(R.id.back)
        backButton.setOnClickListener { onBackPressed() }
        title = findViewById(R.id.title)
        image = findViewById(R.id.image)
        instructions = findViewById(R.id.instructions)
//        getRecipe(id)
    }
    /*
    fun getRecipe(id: String) {
        launch {
            val response = ApiService
            if (response.isSuccessful) {
                val recipe = response.body()?.meals!![0]
                title.text = recipe.strMeal
                Glide.with(this@DetailActivity).load(recipe.strMealThumb).into(image)
                instructions.text = recipe.strInstructions
            } else Toast.makeText(this@DetailActivity, "Error", Toast.LENGTH_SHORT).show()
        }

    }

     */
}