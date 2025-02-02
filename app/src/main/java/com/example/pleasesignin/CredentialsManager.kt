package com.example.pleasesignin

import android.util.Patterns
object CredentialsManager {

    private val credentialsMap = mutableMapOf(Pair("test@te.st", "1234"))

    fun isEmailValid(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS.pattern()
        val regex = Regex(emailPattern)
        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun isFullNameValid(fullName: String): Boolean {
        return fullName.isNotEmpty()
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        val phonePattern = android.util.Patterns.PHONE.pattern()
        val regex = Regex(phonePattern)
        return regex.matches(phoneNumber)
    }

    fun login(email: String, password: String): Boolean {
        return credentialsMap[email.lowercase()]?.equals(password) == true
    }

    fun register(fullName: String, email: String, phoneNumber: String, password: String): String {
        if (credentialsMap[email.lowercase()] != null) {
            return "Email is already taken"
        }
        credentialsMap[email.lowercase()] = password
        return "Registered Successfully"
    }
}
