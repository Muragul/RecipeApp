package com.example.recipeapp.ui.fragment.user

import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*

class LoginFragmentKtTest {

    @Test
    fun validateLoginInput_emptyUsername_returnFalse() {
        val result = validateLoginInput(
            "",
            "muragul"
        )
        assertThat(result, `is`(false))
    }

    @Test
    fun validateLoginInput_emptyPassword_returnFalse() {
        val result = validateLoginInput(
            "muragul",
            ""
        )
        assertThat(result, `is`(false))
    }

    @Test
    fun validateLoginInput_correctData_returnTrue() {
        val result = validateLoginInput(
            "muragul",
            "muragul"
        )
        assertThat(result, `is`(true))
    }
}