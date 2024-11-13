package com.example.pleasesignin

import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class CredentialsManagerTest {
    @Test
//    fun 'Given empty e-mail, then return false'(){
    fun falseOnEmptyEmail() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid(""))
    }

    @Test
    fun falseOnIncorrectInput() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid("тименінедаєш"))
    }

    @Test
    fun falseOnIncompleteEmail_withoutAt() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid("wideprismmusen.pro"))
    }

    @Test
    fun falseOnIncompleteEmail_withoutDot() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid("wideprism@musenpro"))
    }

    @Test
    fun trueOnProperEmail() {
        val credentialsManager = CredentialsManager()
        assertEquals(true, credentialsManager.isEmailValid("wideprism@musen.pro"))
    }
}