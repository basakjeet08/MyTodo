package com.basakjeet08.mytodo.repository

import com.basakjeet08.mytodo.database.TodoDao
import com.basakjeet08.mytodo.model.Todos

/**
 * This class Handles the data calling which can be done by either the Retrofit using APIs or
 * It can be done by the ROOM from the local database.
 *
 * We basically check for the Internet Connection and if the internet connection is there then
 * we call the Data from the Retrofit and updates the data in out local Database
 * and if the Internet isn't there then the data is directly called from the Local database.
 */
//class Repository (private val context: Context){
class Repository (private val todoDao : TodoDao){

    // This function insert new todos to the List by calling the insertTodo function in the DAO
    suspend fun insertTodo(todo : Todos){
        todoDao.insertTodo(todo)
    }

    // This function asks for the List of todos by calling the getTodos function in the DAO
    suspend fun getTodos() : List<Todos>{
        return todoDao.getTodos()
    }
}