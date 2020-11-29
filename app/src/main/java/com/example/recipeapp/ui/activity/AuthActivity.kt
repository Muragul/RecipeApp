package com.example.recipeapp.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    CoroutineScope, LoginFragment.LogInClickListener {
    private lateinit var fragmentManager: FragmentManager
    private var registrationFragment: RegistrationFragment = RegistrationFragment(this, this)
    private var loginFragment: LoginFragment = LoginFragment(this, this)
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
            var id = 0
            withContext(Dispatchers.IO) {
                try {
                    id = userDao?.getUsersCount()!!
                } catch (e: Exception) {
                } finally {
                    val user = User(id + 1, email, username, password)
                    userDao?.registerUser(user)
                    saveUser(user)
                }
            }
        }
    }

    private fun saveUser(user: User) {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        val userEditor = sharedPreferences.edit()
        userEditor.putString("current_user_name", user.username)
        userEditor.putString("current_email", user.email)
        userEditor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginUser(username: String, password: String) {
        launch {
            withContext(Dispatchers.IO) {
                val user = userDao?.getUser(username)
                if (user?.username == username && user.password == password)
                    saveUser(user)
            }
        }
    }

    private fun noSuchUserToast() {
        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
    }

}