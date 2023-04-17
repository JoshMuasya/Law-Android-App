package com.example.law

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var siEmail : EditText
    private lateinit var siPassword : EditText
    private lateinit var confPassword : EditText
    private lateinit var btnRegister : Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        siEmail=findViewById(R.id.siEmailAddress)
        siPassword=findViewById(R.id.registerPass)
        confPassword=findViewById(R.id.confpassword)
        btnRegister=findViewById(R.id.btnRegister)

        auth=FirebaseAuth.getInstance()

        btnRegister.setOnClickListener{
            signUp()
        }

    }

    private fun signUp() {
        val email=siEmail.text.toString()
        val siPassword=siPassword.text.toString()
        val confPassword=confPassword.text.toString()

        if(email.isBlank() || siPassword.isBlank() || confPassword.isBlank()) {
            Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
            return
        } else if(siPassword != confPassword) {
            Toast.makeText(this, "Password does not match!!!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, siPassword).addOnCompleteListener(this) {
            if(it.isSuccessful) {
                Toast.makeText(this, "Lawyer registered Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed!!! Please try again!!!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}