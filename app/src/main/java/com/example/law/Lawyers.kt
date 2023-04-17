package com.example.law

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Lawyers : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lawyers2, container, false)

        val lawyerRecyclerView : RecyclerView = view.findViewById(R.id.lawyerList)
        lawyerRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        lawyerRecyclerView.setHasFixedSize(true)

        val lawyerArrayList : ArrayList<LawyerModel> = arrayListOf()

        val dbref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Lawyers")
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (lawyerSnapshot in snapshot.children) {

                        val lawyer = lawyerSnapshot.getValue(LawyerModel::class.java)
                        lawyerArrayList.add(lawyer!!)

                    }

                    val adapter = LawyerAdapter(lawyerArrayList)
                    adapter.onItemClick = {
                        val intent = Intent(requireActivity(), LawyersDescription::class.java)
                        requireActivity().startActivity(intent)
                    }

                    lawyerRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return view
    }
}