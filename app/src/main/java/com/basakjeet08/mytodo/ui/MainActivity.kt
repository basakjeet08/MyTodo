package com.basakjeet08.mytodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.basakjeet08.mytodo.adapters.MainActivityAdapter
import com.basakjeet08.mytodo.databinding.ActivityMainBinding
import com.basakjeet08.mytodo.model.Todos
import com.basakjeet08.mytodo.repository.Repository
import com.basakjeet08.mytodo.viewmodel.MainActivityViewModel
import com.basakjeet08.mytodo.viewmodel.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter by lazy { MainActivityAdapter(this) }
    private lateinit var mainViewModel : MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        d("Main Activity" , " Before ")
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)


        val repository = Repository(this)
        mainViewModel = ViewModelProvider(this , MainActivityViewModelFactory(repository))[MainActivityViewModel::class.java]


        mainViewModel.myTodoList.observe(this){ todos ->
            adapter.updateList(todos)
        }
        mainViewModel.getTodos()


        binding.btnSubmit.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            d("Main Activity" , title)
            d("Main Activity" , description)
            mainViewModel.insertTodos(Todos(0 , title , description))
        }
    }
}