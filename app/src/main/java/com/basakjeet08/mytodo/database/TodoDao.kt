package com.basakjeet08.mytodo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.basakjeet08.mytodo.model.Todos

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo : Todos)

    @Query("select * from todo_list")
    suspend fun getTodos() : List<Todos>
}