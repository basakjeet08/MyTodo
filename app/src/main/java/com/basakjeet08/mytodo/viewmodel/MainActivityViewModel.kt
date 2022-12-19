package com.basakjeet08.mytodo.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basakjeet08.mytodo.database.TodoDatabase
import com.basakjeet08.mytodo.model.Todos
import com.basakjeet08.mytodo.repository.Repository
import kotlinx.coroutines.launch

// ViewModel class for the Main Activity which contains mostly the Data calls and the Logic of the Activity
class MainActivityViewModel(context: Context) : ViewModel() {

    // Initializing the todoDao object adn then passing it to the Repository so that it can call the required DAO functions
    private val todoDao = TodoDatabase.getDatabase(context).getTodoDao()
    private val repository : Repository = Repository(todoDao)

    // A primary todos list which can only be changed in this class and cannot be changed outside
    private val _myTodosList : MutableLiveData<List<Todos>> = MutableLiveData()

    // A secondary todos list which is passed to the other classes and changing it doesn't affect the main todos list
    val myTodoList : LiveData<List<Todos>>
        get() = _myTodosList

    //This function calls the repository Class which returns the Todos list data
    fun getTodos(){
        viewModelScope.launch {
            val todoList : List<Todos> = repository.getTodos()
            _myTodosList.value = todoList
        }
    }

    // This function calls the repository Class which inserts the new todos to the List and it then
    // calls the getTodos function of the repository to update the todos list and show it on the app realtime
    fun insertTodos(todo : Todos){
        viewModelScope.launch {
            repository.insertTodo(todo)

            // Calling back the repository function to update the Todos List in the ViewModel and then updating in the RecyclerView
            val todoList = repository.getTodos()
            _myTodosList.value = todoList
        }
    }
}