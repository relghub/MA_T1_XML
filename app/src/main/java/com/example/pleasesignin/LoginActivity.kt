package com.example.pleasesignin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.toString

class LoginActivity : AppCompatActivity() {

    private val inputEmail: TextInputLayout
        get() = findViewById(R.id.register_email_field)

    private val inputPassword: TextInputLayout
        get() = findViewById(R.id.register_password_field)

    private val nextButton: Button
        get() = findViewById(R.id.button)

    private val inputEmailText: TextInputEditText
        get() = findViewById(R.id.register_email_field_Text)

    private val inputPasswordText: TextInputEditText
        get() = findViewById(R.id.register_password_field_Text)

    private val isEmailValid: Boolean
        get() = CredentialsManager().isEmailValid(inputEmailText.text.toString())

    private val isPasswordValid: Boolean
        get() = CredentialsManager().isPasswordValid(inputPasswordText.text.toString())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val callToRegisterLabel = findViewById<TextView>(R.id.login_to_register)


        nextButton.setOnClickListener {
            val email = inputEmailText.text.toString()
            val password = inputPasswordText.text.toString()

            if (!isEmailValid) {
                inputEmail.isErrorEnabled = true
                inputEmail.error = "Email is invalid!"
            } else {
                inputEmail.isErrorEnabled = false
            }

            if (!isPasswordValid) {
                inputPassword.isErrorEnabled = true
                inputPassword.error = "Password is invalid!"
            } else {
                inputPassword.isErrorEnabled = false
            }


    }
            callToRegisterLabel.setOnClickListener {
            Log.d("Onboarding", "Register now label pressed")

            val goToRegisterIntent = Intent(this, RegisterActivity::class.java)
            startActivity(goToRegisterIntent)
        }


    }
}