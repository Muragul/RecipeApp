package com.example.recipeapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recipeapp.R

class LoginFragment(private val listener: RedirectToSignUpClickListener) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater
            .inflate(
                R.layout.login_fragment,
                container, false
            ) as ViewGroup

        val redirect: TextView = rootView.findViewById(R.id.to_signup)
        redirect.setOnClickListener {
            listener.redirectToSignUp()
        }
        return rootView
    }

    interface RedirectToSignUpClickListener {
        fun redirectToSignUp()
    }
}