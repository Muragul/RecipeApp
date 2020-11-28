package com.example.recipeapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.data.model.User
import com.example.recipeapp.data.model.UserDao
import com.example.recipeapp.data.model.UserDatabase
import com.example.recipeapp.ui.fragment.LoginFragment
import com.example.recipeapp.ui.fragment.RegistrationFragment
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AuthActivity : AppCompatActivity(), RegistrationFragment.RedirectToLogInClickListener,
    LoginFragment.RedirectToSignUpClickListener, RegistrationFragment.RegisterUserClickListener,
    CoroutineScope {
    private lateinit var fragmentManager: FragmentManager
    private var registrationFragment: RegistrationFragment = RegistrationFragment(this, this)
    private var loginFragment: LoginFragment = LoginFragment(this)
    private var userDao: UserDao? = null
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userDao = UserDatabase.getDatabase(context = this).userDao()

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment, loginFragment).commit()
    }

    override fun redirectToLogIn() {
        fragmentManager.beginTransaction().replace(R.id.fragment, loginFragment).commit()
    }

    override fun redirectToSignUp() {
        fragmentManager.beginTransaction().replace(R.id.fragment, registrationFragment).commit()
    }

    override fun registerUser(email: String, username: String, password: String) {
        launch {
            val user = User(1, email, username, password)
            withContext(Dispatchers.IO) {
                userDao?.registerUser(user)
            }
        }
        val intent = Intent(this, FoodActivity::class.java)
        startActivity(intent)
    }
}