package com.basakjeet08.mytodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basakjeet08.mytodo.model.Todos
import com.basakjeet08.mytodo.repository.Repository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    private val _myTodosList : MutableLiveData<List<Todos>> = MutableLiveData()
    val myTodoList : LiveData<List<Todos>>
        get() = _myTodosList
    fun getTodos(){
        viewModelScope.launch {
            val todoList : List<Todos> = repository.getTodos()
            _myTodosList.value = todoList
        }
    }

    fun insertTodos(todo : Todos){
        viewModelScope.launch {
            repository.insertTodo(todo)
            val todoList = repository.getTodos()
            _myTodosList.value = todoList
        }
    }
}