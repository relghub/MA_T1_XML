package com.example.pleasesignin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private var isFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, FragmentA())
            .commit()

        val switchButton = findViewById<Button>(R.id.switch_button)

        switchButton.setOnClickListener {

            val currentFragment: Fragment = if (isFragmentA) {
                FragmentB()
            } else {
                FragmentA()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, currentFragment)
                .commit()

            isFragmentA = !isFragmentA
        }
    }
}