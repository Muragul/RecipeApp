package com.example.recipeapp.ui.fragment.user

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
    ): View {
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

    private fun fillOutAllFieldsToast() {
        Toast.makeText(context, "Please, fill out all fields", Toast.LENGTH_SHORT).show()
    }

    interface RedirectToLogInClickListener {
        fun redirectToLogIn()
    }

    interface RegisterUserClickListener {
        fun registerUser(email: String, username: String, password: String)
    }

}