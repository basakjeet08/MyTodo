package com.basakjeet08.mytodo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class Todos(
    @PrimaryKey(autoGenerate = true)
    val todoId : Int ,
    val todoTitle : String ,
    val todoDescription : String
)
