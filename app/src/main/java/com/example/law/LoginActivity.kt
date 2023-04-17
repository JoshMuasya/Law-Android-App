package com.example.law

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var lgEmail: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lgEmail = findViewById(R.id.lgEmailAddress)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogin)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            login()
        }
    }

    private fun login() {
        val email = lgEmail.text.toString()
        val pass = password.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if(it.isSuccessful) {
                Toast.makeText(this, "You are logged in Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Log in Failed!! Please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}