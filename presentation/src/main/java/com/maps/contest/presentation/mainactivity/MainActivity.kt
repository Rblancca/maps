package com.maps.contest.presentation.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maps.contest.myapplication.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}