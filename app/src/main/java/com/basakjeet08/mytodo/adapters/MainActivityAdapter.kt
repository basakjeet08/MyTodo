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

    // List which contains all the TODOs and then it is displayed in the RecyclerView
    private var todos : List<Todos>? = null

    //Step-2 :- ViewModel class which initializes the View Objects and give variables that can be used later
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //Initializing the View elements
        val tvTodoTitle : TextView = itemView.findViewById(R.id.tvTodoTitle)
        val tvTodoDescription : TextView = itemView.findViewById(R.id.tvTodoDescription)
    }

    // Step-1 :- It basically inflates the views to the parent layout from where it is called
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Inflating the View
        val view = LayoutInflater.from(context).inflate(R.layout.item_todo_row , parent , false)
        return ViewHolder(view)
    }

    //Step-2 :- It binds the data to the View elements in the RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTodoTitle.text = context.getString(R.string.title_s , todos?.get(position)!!.todoTitle)
        holder.tvTodoDescription.text = context.getString(R.string.description_s , todos?.get(position)!!.todoDescription)
    }

    //It returns the Count of the list
    override fun getItemCount(): Int {
        return todos?.size!!
    }

    //This function updates the TODOs list which is being observed
    fun updateList(newTodos : List<Todos>){
        todos = newTodos
        notifyDataSetChanged()
    }
}