package com.example.recipeapp.ui.fragment.user

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`

class RegistrationFragmentKtTest {

    @Test
    fun validateRegistrationInput_emptyUsername_returnFalse() {
        val result = validateRegistrationInput(
            "muragulm@gmail.com",
            "",
            "muragul"
        )
        assertThat(result, `is`(false))
    }

    @Test
    fun validateRegistrationInput_emptyPassword_returnFalse() {
        val result = validateRegistrationInput(
            "muragulm@gmail.com",
            "muragul",
            ""
        )
        assertThat(result, `is`(false))
    }

    @Test
    fun validateRegistrationInput_correctData_returnTrue() {
        val result = validateRegistrationInput(
            "muragulm@gmail.com",
            "muragul",
            "muragul"
        )
        assertThat(result, `is`(true))
    }
}