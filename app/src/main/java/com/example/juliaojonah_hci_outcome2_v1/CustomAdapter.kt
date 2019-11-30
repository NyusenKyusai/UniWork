package com.example.juliaojonah_hci_outcome2_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CustomAdapter(val passwordList: ArrayList<PasswordCluster>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return passwordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val passwordCluster: PasswordCluster = passwordList[position]

        holder?.description?.text = passwordCluster.description
        holder?.password?.text = passwordCluster.passwordCluster
        holder?.dateTime?.text = passwordCluster.dateTime

    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val description = itemView.findViewById(R.id.description) as TextView
        val password = itemView.findViewById(R.id.password) as TextView
        val dateTime = itemView.findViewById(R.id.dateTime) as TextView
    }
}