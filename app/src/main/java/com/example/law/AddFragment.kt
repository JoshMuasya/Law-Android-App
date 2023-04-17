package com.example.law

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        val database : FirebaseDatabase = FirebaseDatabase.getInstance()

        val myRef = database.getReference("Lawyers")

        val addLawyer : Button = view.findViewById(R.id.addLawyer)

        addLawyer.setOnClickListener {
            val etName : EditText = view.findViewById(R.id.lawyerName)
            val etNumber : EditText = view.findViewById(R.id.lawyerNumber)
            val etEmail : EditText = view.findViewById(R.id.lawyerEmail)

            val name : String = etName.text.toString()
            val number : String = etNumber.text.toString()
            val email : String = etEmail.text.toString()

            if(name.isBlank() || number.isBlank() || email.isBlank()) {
                Toast.makeText(requireContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {

                val query = myRef.orderByChild("name").equalTo(name)

                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()) {
                            Toast.makeText(requireContext(), "Lawyer already exist", Toast.LENGTH_SHORT).show()
                        } else {
                            val lawyer = Lawyer(name, number, email)

                            myRef.push().setValue(lawyer).addOnSuccessListener {
                                Toast.makeText(requireContext(), "Lawyer added Successfully", Toast.LENGTH_SHORT).show()
                                etName.setText("")
                                etNumber.setText("")
                                etEmail.setText("")
                            }.addOnFailureListener {
                                Toast.makeText(requireContext(), "Error adding Lawyer", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(requireContext(), "Error checking for Lawyer", Toast.LENGTH_SHORT).show()
                    }
                })

            }

        }

        return view

    }

    data class Lawyer(val name: String, val number: String, val email: String) {

    }
}