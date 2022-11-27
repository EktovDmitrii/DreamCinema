package com.example.dreamcinema.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.data.network.api.ApiFactory
import com.example.dreamcinema.data.network.api.ApiService
import com.example.dreamcinema.data.network.api.RemoteDataSource
import com.example.dreamcinema.databinding.ActivityMainBinding
import com.example.dreamcinema.presentation.adapter.MovieInfoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MovieInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var adapter: MovieInfoAdapter


    private val component by lazy {
        (application as MovieApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieInfoViewModel::class.java]

        adapter = MovieInfoAdapter()
        binding.rvFilmInfoList.adapter = adapter
        setObservers()
        viewModel.getTopMovieList()
    }

    private fun setObservers() {
        viewModel.listMovie.observe(this) { movieInfo ->
            adapter.myData = movieInfo
            adapter.notifyDataSetChanged()
        }
    }
}