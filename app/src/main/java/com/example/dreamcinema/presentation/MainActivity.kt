package com.example.dreamcinema.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dreamcinema.R
import com.example.dreamcinema.data.ApiFactory
import com.example.dreamcinema.data.ApiFactory.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            loadData()
        }
    }


   private suspend fun loadData(){
        val topMovies = ApiFactory.apiService.getTopMoviesInfo()
        Log.d("tagtag", "${topMovies.toString()}")
    }
}