package com.example.recipeapp.ui.fragment

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

class LoginFragment(
    private val listener: RedirectToSignUpClickListener,
    private val loginListener: LogInClickListener
) : Fragment() {
    private lateinit var logInButton: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater
            .inflate(
                R.layout.login_fragment,
                container, false
            ) as ViewGroup

        logInButton = rootView.findViewById(R.id.login_button)
        logInButton.setOnClickListener {
            username = rootView.findViewById(R.id.login)
            password = rootView.findViewById(R.id.password)
            if (username.text.isBlank() || password.text.isBlank())
                fillOutAllFieldsToast()
            else
                loginListener.loginUser(
                    username.text.toString(),
                    password.text.toString()
                )
        }
        val redirect: TextView = rootView.findViewById(R.id.to_signup)
        redirect.setOnClickListener {
            listener.redirectToSignUp()
        }
        return rootView
    }

    interface RedirectToSignUpClickListener {
        fun redirectToSignUp()
    }

    interface LogInClickListener {
        fun loginUser(username: String, password: String)
    }

    private fun fillOutAllFieldsToast() {
        Toast.makeText(context, "Please, fill out all fields", Toast.LENGTH_SHORT).show()
    }
}