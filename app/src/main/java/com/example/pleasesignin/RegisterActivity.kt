package com.example.pleasesignin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private val inputEmail: TextInputLayout
        get() = findViewById(R.id.register_email_field)

    private val inputEmailText: TextInputEditText
        get() = findViewById(R.id.register_email_field_text)

    private val inputPasswordLayout: TextInputLayout
        get() = findViewById(R.id.register_password_field)

    private val inputPasswordText: TextInputEditText
        get() = findViewById(R.id.register_password_field_text)

    private val inputFullName: TextInputLayout
        get() = findViewById(R.id.register_full_name_field)

    private val inputFullNameText: TextInputEditText
        get() = findViewById(R.id.register_full_name_field_text)

    private val inputPhoneNumber: TextInputLayout
        get() = findViewById(R.id.register_phone_field)

    private val inputPhoneNumberText: TextInputEditText
        get() = findViewById(R.id.register_phone_field_text)

    private val nextButton: Button
        get() = findViewById(R.id.button)

    private val checkBox: CheckBox
        get() = findViewById(R.id.checkBox)

    private val isEmailValid: Boolean
        get() = CredentialsManager().isEmailValid(inputEmailText.text.toString())

    private val isFullNameValid: Boolean
        get() = CredentialsManager().isFullNameValid(inputFullNameText.text.toString())

    private val isPhoneNumberValid: Boolean
        get() = CredentialsManager().isPhoneNumberValid(inputPhoneNumberText.text.toString())

    private val isPasswordValid: Boolean
        get() = CredentialsManager().isPasswordValid(inputPasswordText.text.toString())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val callToLoginLabel = findViewById<TextView>(R.id.register_to_login)

        nextButton.setOnClickListener {
            var isValid = true
            val email = inputEmailText.text.toString()
            val password = inputPasswordText.text.toString()
            val phoneNumber = inputPhoneNumberText.text.toString()
            val fullName = inputFullNameText.text.toString()

            if (!isEmailValid) {
                isValid = false
                inputEmail.isErrorEnabled = true
                inputEmail.error = "Email is invalid!"
            } else {
                inputEmail.isErrorEnabled = false
            }

            if (!isPasswordValid) {
                isValid = false
                inputPasswordLayout.isErrorEnabled = true
                inputPasswordLayout.error = "Password is invalid!"
            } else {
                inputPasswordLayout.isErrorEnabled = false
            }

            if (!isPhoneNumberValid) {
                isValid = false
                inputPhoneNumber.isErrorEnabled = true
                inputPhoneNumber.error = "Phone number is invalid!"
            } else {
                inputPhoneNumber.isErrorEnabled = false
            }

            if (!isFullNameValid) {
                isValid = false
                inputFullName.isErrorEnabled = true
                inputFullName.error = "Phone number is invalid!"
            } else {
                inputFullName.isErrorEnabled = false
            }

            if(!checkBox.isChecked()){
                isValid = false
                Toast.makeText(this, "Please approve the terms and conditions.", Toast.LENGTH_SHORT).show()
            }

            if (isValid) {
                val returned = CredentialsManager().register(fullName, phoneNumber, email, password)

                Log.v("returned", returned)

                if(returned == "Registered Successfully") {
                    val goToLoginIntent = Intent(this, LoginActivity::class.java)
                    startActivity(goToLoginIntent)
                }else {
                    Toast.makeText(this, returned, Toast.LENGTH_SHORT).show()
                }
            }
        }

        callToLoginLabel.setOnClickListener {
            Log.d("Onboarding", "Login label pressed")

            val goToLoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(goToLoginIntent)
        }
    }
}
