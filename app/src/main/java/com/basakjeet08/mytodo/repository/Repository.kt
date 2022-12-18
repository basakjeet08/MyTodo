package com.basakjeet08.mytodo.repository

import android.content.Context
import com.basakjeet08.mytodo.database.TodoDatabase
import com.basakjeet08.mytodo.model.Todos

class Repository (private val context: Context){

    suspend fun insertTodo(todo : Todos){
        TodoDatabase.getDatabase(context).todoDao().insertTodo(todo)
    }
    suspend fun getTodos() : List<Todos>{
        return TodoDatabase.getDatabase(context).todoDao().getTodos()
    }
}