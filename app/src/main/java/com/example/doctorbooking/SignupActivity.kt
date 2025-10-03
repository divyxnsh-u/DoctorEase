package com.example.doctorbooking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnCreate = findViewById<Button>(R.id.btnCreate)

        btnCreate.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter Email & Password", Toast.LENGTH_SHORT).show()
            } else if (!isValidGmail(email)) {
                Toast.makeText(this, "Enter a valid Gmail address (example@gmail.com)", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DoctorListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
    private fun isValidGmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com")
    }
}
