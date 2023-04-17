package com.example.law

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LawyerAdapter(private val lawyerList : ArrayList<LawyerModel>) :
    RecyclerView.Adapter<LawyerAdapter.MyViewHolder>() {

    var onItemClick: ((LawyerModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return lawyerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = lawyerList[position]

        holder.name.text = currentItem.name
        holder.email.text = currentItem.email
        holder.number.text = currentItem.number

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
            val intent = Intent(holder.itemView.context, LawyerFragment::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.name)
        val number : TextView = itemView.findViewById(R.id.number)
        val email : TextView = itemView.findViewById(R.id.email)


    }

}