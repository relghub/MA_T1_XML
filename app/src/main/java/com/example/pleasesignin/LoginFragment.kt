package com.example.pleasesignin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.toString

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputEmail = view.findViewById<TextInputEditText>(R.id.register_email_field_Text)
        val inputPassword = view.findViewById<TextInputEditText>(R.id.register_password_field_Text)
        val nextButton = view.findViewById<Button>(R.id.button)
        val callToRegisterLabel = view.findViewById<TextView>(R.id.login_to_register)

        nextButton.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (CredentialsManager.login(email, password)) {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to the main app logic or next screen
            } else {
                inputPassword.error = "Incorrect email or password"
            }
        }

        callToRegisterLabel.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}