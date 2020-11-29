package com.example.recipeapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.recipeapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var foodPageButton: ConstraintLayout
    private lateinit var barPageButton: ConstraintLayout
    private lateinit var profilePageButton: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodPageButton = findViewById(R.id.food)
        barPageButton = findViewById(R.id.bar)
        profilePageButton = findViewById(R.id.profile)

        foodPageButton.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            startActivity(intent)
        }
        barPageButton.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }
        profilePageButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


    }


}
