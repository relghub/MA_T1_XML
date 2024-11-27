package com.example.pleasesignin

import android.os.Debug
import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class CredentialsManagerTest {
    @Test
//    fun 'Given empty e-mail, then return false'(){
    fun falseOnEmptyEmail() {
        val credentialsManager = CredentialsManager()
        val email = ""

        val result = credentialsManager.isEmailValid(email)
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

    @Test
    fun falseOnIncorrectCredentials() {
        val credentialsManager = CredentialsManager()
        val email = "Not a user"
        val password = "Not a password"

        val result = credentialsManager.login(email, password)
        assertFalse(result)
    }

    @Test
    fun trueOnFixedCredentials() {
        val credentialsManager = CredentialsManager()
        val email = "test"
        val password = "1234"

        val result = credentialsManager.login(email, password)
        assertTrue(result)
    }

    @Test
    fun givenProperCredentials_whenUserRegisters_thenCreateAccount() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("John", "another@te.st", "600 600 000", "12234")

        val isLoginSuccess = credentialsManager.login("another@te.st", "12234")

        assertTrue(isLoginSuccess)
    }
}