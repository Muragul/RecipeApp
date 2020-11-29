package com.example.recipeapp.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.R
import com.example.recipeapp.ui.activity.auth.AuthActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("current_user_name", null)
        if (username != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}