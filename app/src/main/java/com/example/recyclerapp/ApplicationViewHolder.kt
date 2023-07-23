package com.example.recyclerapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val textView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}