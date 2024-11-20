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

        val inputEmail = findViewById<TextInputLayout>(R.id.register_email_field)
        val inputPassword = findViewById<TextInputLayout>(R.id.register_password_field)

        val inputEmailText = findViewById<TextInputEditText>(R.id.register_email_field_Text)
        val inputPasswordText = findViewById<TextInputEditText>(R.id.register_password_field_Text)

        val nextButton = findViewById<Button>(R.id.button)
        nextButton.setOnClickListener {
            val email = inputEmailText.text.toString()
            val password = inputPasswordText.text.toString()

            val isEmailValid = CredentialsManager().isEmailValid(email)
            val isPasswordValid = CredentialsManager().isPasswordValid(password)

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