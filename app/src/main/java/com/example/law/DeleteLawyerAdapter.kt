package com.example.law

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class DeleteLawyerAdapter(private val deleteList : ArrayList<DeleteLawyerModel>) :
        RecyclerView.Adapter<DeleteLawyerAdapter.MyViewHolder>() {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var myRef = database.getReference("Lawyers")
    private var query : Query = FirebaseDatabase.getInstance().getReference("Lawyers")

    init {
        myRef.child("Lawyers").orderByChild("name")
        query.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                deleteList.clear()
                for(snapshot in dataSnapshot.children) {
                    val deleteLawyer = snapshot.getValue(DeleteLawyerModel::class.java)
                    deleteLawyer?.let {
                        deleteList.add(it)
                    }
                }
                notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return deleteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = deleteList[position]

        holder.name.text = currentItem.name
        holder.number.text = currentItem.number
        holder.email.text = currentItem.email
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.name)
        val number : TextView = itemView.findViewById(R.id.number)
        val email : TextView = itemView.findViewById(R.id.email)
    }

}