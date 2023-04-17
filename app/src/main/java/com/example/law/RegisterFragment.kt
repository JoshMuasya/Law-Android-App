package com.example.law

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var siEmail : EditText
    private lateinit var siPass : EditText
    private lateinit var confPass : EditText
    private lateinit var btnRegister : Button

    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        siEmail = view.findViewById(R.id.siEmailAddress)
        siPass = view.findViewById(R.id.registerPass)
        confPass = view.findViewById(R.id.confpassword)
        btnRegister = view.findViewById(R.id.btnRegister)

        auth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener {
            signUp()
        }

        return view
    }

    private fun signUp() {
        val email=siEmail.text.toString()
        val siPass=siPass.text.toString()
        val confPass=confPass.text.toString()

        if(email.isBlank() || siPass.isBlank() || confPass.isBlank()) {
            Toast.makeText(requireActivity(), "Fill in all the fields", Toast.LENGTH_SHORT).show()
            return
        } else if (siPass != confPass) {
            Toast.makeText(requireActivity(), "Password does not match!!!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, siPass).addOnCompleteListener(requireActivity()) {
            if(it.isSuccessful) {
                Toast.makeText(requireActivity(), "User registered Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), Dashboard::class.java)
                requireActivity().startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireActivity(), "Failed!!! Please try again!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}