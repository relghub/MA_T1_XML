package com.example.pleasesignin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.toString

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton = view.findViewById<Button>(R.id.button)

        val inputEmail = view.findViewById<TextInputLayout>(R.id.register_email_layout)
        val inputEmailText = view.findViewById<TextInputEditText>(R.id.register_email_field)

        val inputPassword = view.findViewById<TextInputLayout>(R.id.register_password_layout)
        val inputPasswordText = view.findViewById<TextInputEditText>(R.id.register_password_field)

        val inputFullName = view.findViewById<TextInputLayout>(R.id.register_full_name_layout)
        val inputFullNameText = view.findViewById<TextInputEditText>(R.id.register_full_name_field)

        val inputPhoneNumber = view.findViewById<TextInputLayout>(R.id.register_phone_layout)
        val inputPhoneNumberText = view.findViewById<TextInputEditText>(R.id.register_phone_field)

        val callToLoginLabel = view.findViewById<TextView>(R.id.register_to_login)

        registerButton.setOnClickListener {
            Log.d("RegisterFragment", "Register button clicked")

            val email = inputEmailText.text.toString()
            val password = inputPasswordText.text.toString()
            val fullName = inputFullNameText.text.toString()
            val phoneNumber = inputPhoneNumberText.text.toString()

            if (!CredentialsManager.isEmailValid(email)) {
                inputEmail.error = "Invalid email"
                return@setOnClickListener
            }

            if (!CredentialsManager.isPasswordValid(password)) {
                inputPassword.error = "Invalid password"
                return@setOnClickListener

            }

            if (!CredentialsManager.isFullNameValid(fullName)) {
                inputFullName.error = "Invalid full name"
                return@setOnClickListener

            }

            if (!CredentialsManager.isPhoneNumberValid(phoneNumber)) {
                inputPhoneNumber.error = "Invalid phone number"
                return@setOnClickListener

            }

            val result = CredentialsManager.register("david", email, "123456789", password)
            Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()

            if (result == "Registered Successfully") {
                callToLoginLabel.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }


    }
}