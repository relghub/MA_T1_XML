package com.example.pleasesignin

import android.util.Patterns

class CredentialsManager {

    val credentialsMap = mutableMapOf(Pair("test", "1234"))

    fun isEmailValid(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(emailPattern)

        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun login(email: String, password: String): Boolean {
        return credentialsMap[email]?.equals(password) == true
    }

    fun register(fullName: String, email: String, phoneNumber: String, password: String) {
        credentialsMap.put(email, password);
    }
}