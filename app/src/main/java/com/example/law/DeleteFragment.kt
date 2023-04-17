package com.example.law

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

class DeleteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delete, container, false)

        val deleteLawyerView : RecyclerView = view.findViewById(R.id.deleteLawyerList)
        deleteLawyerView.layoutManager = LinearLayoutManager(requireActivity())
        deleteLawyerView.setHasFixedSize(true)

        val deleteArrayList : ArrayList<DeleteLawyerModel> = arrayListOf()

        val dbref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Lawyers")
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for (deleteSnapshot in snapshot.children) {

                        val delete = deleteSnapshot.getValue(DeleteLawyerModel::class.java)
                        deleteArrayList.add(delete!!)
                    }

                    val adapter = DeleteLawyerAdapter(deleteArrayList)
                    deleteLawyerView.adapter = adapter

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return view
    }
}