package com.example.pleasesignin

import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class CredentialsManagerTest {
    @Test
//    fun 'Given empty e-mail, then return false'(){
    fun falseOnEmptyEmail() {
        val credentialsManager = CredentialsManager()
        val email = ""

        val result = credentialsManager.isEmailValid("")
        assertFalse(result)
    }

    @Test
    fun falseOnIncorrectInput() {
        val credentialsManager = CredentialsManager()
        val email = "тименінедаєш"

        val result = credentialsManager.isEmailValid(email)
        assertFalse(result)
    }

    @Test
    fun falseOnIncompleteEmail_withoutAt() {
        val credentialsManager = CredentialsManager()
        val email = "wideprismmusen.pro"

        val result = credentialsManager.isEmailValid(email)
        assertFalse(result)
    }

    @Test
    fun falseOnIncompleteEmail_withoutDot() {
        val credentialsManager = CredentialsManager()
        val email = "wideprism@musenpro"

        val result = credentialsManager.isEmailValid(email)
        assertFalse(result)
    }

    @Test
    fun trueOnProperEmail() {
        val credentialsManager = CredentialsManager()
        val email = "wideprism@musen.pro"

        val result = credentialsManager.isEmailValid(email)
        assertTrue(result)
    }

    @Test
    fun falseOnEmptyPassword() {
        val credentialsManager = CredentialsManager()
        val password = ""

        val result = credentialsManager.isPasswordValid(password)
        assertFalse(result)
    }

    @Test
    fun trueOnNonEmptyPassword() {
        val credentialsManager = CredentialsManager()
        val password = "123456"

        val result = credentialsManager.isPasswordValid(password)
        assertTrue(result)
    }
}