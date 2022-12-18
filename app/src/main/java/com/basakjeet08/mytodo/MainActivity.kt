package com.basakjeet08.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.basakjeet08.mytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}