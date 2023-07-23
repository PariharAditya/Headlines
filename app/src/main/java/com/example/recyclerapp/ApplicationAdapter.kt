package com.example.recyclerapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ApplicationAdapter(private val listener: NewsItem): RecyclerView.Adapter<ApplicationViewHolder>()
{
    private val items:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ApplicationViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.objects, parent, false)
        val viewHolder = ApplicationViewHolder(view)

        view.setOnClickListener()
        {
            listener.onItemSelected(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int)
    {
        val item = items[position]
        holder.textView.text = item.title
    }

    override fun getItemCount(): Int
    {
        return items.size
    }
}
interface NewsItem
{
    fun onItemSelected(item: News)
    {
        // Do nothing
    }
}