package com.example.recipeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.ui.fragment.LoginFragment
import com.example.recipeapp.ui.fragment.RegistrationFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private var registrationFragment: RegistrationFragment = RegistrationFragment()
    private var loginFragment: LoginFragment = LoginFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, loginFragment).commit()
    }
}