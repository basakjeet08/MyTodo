package com.basakjeet08.mytodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.basakjeet08.mytodo.adapters.MainActivityAdapter
import com.basakjeet08.mytodo.databinding.ActivityMainBinding
import com.basakjeet08.mytodo.model.Todos
import com.basakjeet08.mytodo.viewmodel.MainActivityViewModel
import com.basakjeet08.mytodo.viewmodel.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    // The Binding, ViewModel and the adapter declaring is done here
    private lateinit var binding : ActivityMainBinding
    private val adapter by lazy { MainActivityAdapter(this) }
    private lateinit var mainViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting up the RecyclerView adapters and LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        // Creating the ViewModel Object using the ViewModelFactory
        mainViewModel = ViewModelProvider(this , MainActivityViewModelFactory(this))[MainActivityViewModel::class.java]

        //Setting Up an Observable to observe the Todolist for changes and if it changes it notifies the recyclerView
        mainViewModel.myTodoList.observe(this){ todos ->
            adapter.updateList(todos)
        }

        //Calling the function to fetch the data from the Database at least once
        mainViewModel.getTodos()

        //On click Listener for the Submit Button ... It basically takes the string and then passes it to be inserted to the Database
        binding.btnSubmit.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            // Calling the ViewModel object for the Data related Stuff
            mainViewModel.insertTodos(Todos(0 , title , description))
        }
    }
}