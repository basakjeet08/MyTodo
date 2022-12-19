package com.basakjeet08.mytodo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basakjeet08.mytodo.R
import com.basakjeet08.mytodo.model.Todos

class MainActivityAdapter(private val context : Context) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {
    private var todos : List<Todos>? = null
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvTodoTitle : TextView = itemView.findViewById(R.id.tvTodoTitle)
        val tvTodoDescription : TextView = itemView.findViewById(R.id.tvTodoDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_todo_row , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTodoTitle.text = context.getString(R.string.title_s , todos?.get(position)!!.todoTitle)
        holder.tvTodoDescription.text = context.getString(R.string.description_s , todos?.get(position)!!.todoDescription)
    }

    override fun getItemCount(): Int {
        return todos?.size!!
    }
    fun updateList(newTodos : List<Todos>){
        todos = newTodos
        notifyDataSetChanged()
    }
}