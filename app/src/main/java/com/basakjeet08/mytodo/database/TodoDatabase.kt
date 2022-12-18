package com.basakjeet08.mytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.basakjeet08.mytodo.model.Todos

@Database(entities = [Todos::class] , version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao() : TodoDao

    companion object{
        @Volatile
        private var databaseInstance : TodoDatabase? = null

        fun getDatabase(context: Context) : TodoDatabase{
            if(databaseInstance == null){
                synchronized(this){
                    databaseInstance = Room.databaseBuilder(context ,
                        TodoDatabase::class.java , "TodoDB")
                        .build()
                }
            }
            return databaseInstance!!
        }
    }
}