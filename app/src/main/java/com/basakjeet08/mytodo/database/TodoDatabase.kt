package com.basakjeet08.mytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.basakjeet08.mytodo.model.Todos

/**
 * Database class inherits the RoomDatabase class and it is used to implement the DAO functions
 * and after that we can use the Implementations given by the Room Database class
 *
 * We Make a companion object of this class only so no multiple object of this class can be made
 */

//Database entity are provided here and the Database Version is Mentioned here
@Database(entities = [Todos::class] , version = 1)
abstract class TodoDatabase : RoomDatabase(){

    // It is a abstract function which returns the DAO object from the implemented class in Room Database
    abstract fun getTodoDao() : TodoDao

    // Companion Object is made to make a variable of this class and to ensure that only one object runs throughout the program
    companion object{

        // Volatile Keyword basically means that if the value of the Instance changes then it inform the other classes
        @Volatile
        private var INSTANCE : TodoDatabase? = null         // Database class Variable

        //This function returns the database class object to its caller accordingly
        fun getDatabase(context: Context): TodoDatabase {

            // If the database Instance is already created then it is not created again else it is created
            if (INSTANCE == null) {

                // We use Synchronized to make sure that only one Operation is done at a time in the Database
                synchronized(this) {

                    /**
                     *  Building the Room Entity,DAOs,Database using the Room.databaseBuilder function
                     *
                     *  It takes the context of the Activity from which the data fetch request is done
                     *  It takes the Database Class which needs to be implemented and from where the
                     *  DAO implementations are done too.
                     *  Lastly it takes a name for the DataBase .
                     */
                    INSTANCE = Room.databaseBuilder(
                        context,
                        TodoDatabase::class.java, "TodoDB"
                    )
                        .build()
                }
            }
            //Returning the Database instance using which we can call the above todoDao method to implement the DAO
            return INSTANCE!!
        }
    }
}