package com.example.recipeapp.ui.activity.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.bar.DrinkResponse
import com.example.recipeapp.data.model.food.Meal
import com.example.recipeapp.data.model.food.MealResponse
import com.example.recipeapp.data.model.user.*
import com.example.recipeapp.ui.activity.MainActivity
import com.example.recipeapp.ui.fragment.user.LoginFragment
import com.example.recipeapp.ui.fragment.user.RegistrationFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
                    redirectToMainPage()
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
    }

    private fun redirectToMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun loginUser(username: String, password: String) {
        launch {
            withContext(Dispatchers.IO) {
                val user = userDao?.getUser(username)
                if (user?.username == username && user.password == password){
                    saveUser(user)
                    initRecipeLists()
                    redirectToMainPage()
                }
            }
        }
    }

    private fun noSuchUserToast() {
        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
    }

    private fun initRecipeLists() {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("current_user_name", null)
        val sharedPreferencesRecipeLists: SharedPreferences =
            this.getSharedPreferences(username, Context.MODE_PRIVATE)
        val foodListGson = sharedPreferencesRecipeLists.getString("food_recipe_list", null)
        val barListGson = sharedPreferencesRecipeLists.getString("bar_recipe_list", null)
        val recentFoodListGson =
            sharedPreferencesRecipeLists.getString("recent_food_recipe_list", null)
        val recentBarListGson =
            sharedPreferencesRecipeLists.getString("recent_bar_recipe_list", null)
        val gson = GsonBuilder().create()
        if (foodListGson != null) {
            val foodList = gson.fromJson(foodListGson, Array<Meal>::class.java).toMutableList()
            SavedRecipeList.initFoodRecipeList(foodList as ArrayList<Meal>)
        }
        if (barListGson != null) {
            val barList = gson.fromJson(barListGson, Array<Drink>::class.java).toMutableList()
            SavedRecipeList.initBarRecipeList(barList as ArrayList<Drink>)
        }
        if (recentFoodListGson != null) {
            val recentFoodList = gson.fromJson(recentFoodListGson, Array<Meal>::class.java).toMutableList()
            RecentRecipeList.initFoodRecipeList(recentFoodList as ArrayList<Meal>)
        }
        if (recentBarListGson != null) {
            val recentBarList = gson.fromJson(recentBarListGson, Array<Drink>::class.java).toMutableList()
            RecentRecipeList.initBarRecipeList(recentBarList as ArrayList<Drink>)
        }
    }

}