package com.example.githubrepos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.githubrepos.R
import com.example.githubrepos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNavigate.setOnClickListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            findNavController(R.id.fragmentContainer).navigate(R.id.action_homFragment_to_detailsFragment)

        }
    }
}