package com.basakjeet08.mytodo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.basakjeet08.mytodo.model.Todos

/**
 * This DAO class basically means Data Accessing Objects i.e this class basically insert
 * the data or update or delete the Data from the Room Database directly
 *
 * This is just a implementation and the functions are defined by ROOM and so we need to do less
 * boilerplate code and can implement it easily
 */
@Dao
interface TodoDao {

    // Implementation to insert an entry to the Database or Entity
    @Insert
    suspend fun insertTodo(todo : Todos)

    // Implementation to get all the entries in the Database or the Entity
    @Query("select * from todo_list")
    suspend fun getTodos() : List<Todos>
}