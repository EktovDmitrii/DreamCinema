package com.example.dreamcinema.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dreamcinema.R
import com.example.dreamcinema.data.network.api.ApiFactory
import com.example.dreamcinema.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }


   private suspend fun loadData(){
        val topMovies = ApiFactory.apiService.getTopMoviesInfo()
        Log.d("tagtag", "${topMovies.toString()}")
    }
}