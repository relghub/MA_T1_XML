package com.example.pleasesignin

import android.util.Patterns

class CredentialsManager {
    fun isEmailValid(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(emailPattern)

        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}