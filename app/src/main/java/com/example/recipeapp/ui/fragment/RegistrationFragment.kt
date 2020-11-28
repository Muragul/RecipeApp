package com.example.recipeapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recipeapp.R
import com.example.recipeapp.data.model.User
import com.example.recipeapp.data.model.UserDao
import com.example.recipeapp.data.model.UserDatabase

class RegistrationFragment(
    private val listener: RedirectToLogInClickListener,
    private val registrationListener: RegisterUserClickListener
) : Fragment() {
    private lateinit var signUpButton: Button
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater
            .inflate(
                R.layout.registration_fragment,
                container, false
            ) as ViewGroup
        signUpButton = rootView.findViewById(R.id.signup_button)
        signUpButton.setOnClickListener {
            email = rootView.findViewById(R.id.email)
            username = rootView.findViewById(R.id.username)
            password = rootView.findViewById(R.id.password)

            if (email.text.isBlank() || username.text.isBlank() || password.text.isBlank())
                fillOutAllFieldsToast()
            else
                registrationListener.registerUser(
                    email.text.toString(),
                    username.text.toString(),
                    password.text.toString()
                )
        }
        val redirect: TextView = rootView.findViewById(R.id.to_login)
        redirect.setOnClickListener {
            listener.redirectToLogIn()
        }

        return rootView
    }

/*    private fun saveUser(user: User) {
        val sharedPreferences: SharedPreferences =
            context!!.getSharedPreferences("current_user", Context.MODE_PRIVATE)
        val userEditor = sharedPreferences.edit()
        val userString = user.username + "," + user.password
        userEditor.putString("current_user", userString)
        userEditor.apply()
    }

 */

    private fun fillOutAllFieldsToast() {
        Toast.makeText(context, "Please, fill out all fields", Toast.LENGTH_SHORT).show()
    }

    private fun suchUserAlreadyExistsToast() {
        Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show()
    }

    interface RedirectToLogInClickListener {
        fun redirectToLogIn()
    }

    interface RegisterUserClickListener {
        fun registerUser(email: String, username: String, password: String)
    }

}