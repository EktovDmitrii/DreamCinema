package com.example.dreamcinema.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.databinding.ActivityMainBinding
import com.example.dreamcinema.presentation.adapter.MovieInfoAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MovieInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MovieApp).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = MovieInfoAdapter(this)
        binding.rvFilmInfoList.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)[MovieInfoViewModel::class.java]
        viewModel.movieInfoList.observe(this) {
            adapter.submitList(it)
        }
    }


}