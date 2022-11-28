package com.example.dreamcinema.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.databinding.FragmentHomeBinding
import com.example.dreamcinema.databinding.FragmentHomeHorizontalBinding
import com.example.dreamcinema.domain.MovieList

class VerticalMovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: FragmentHomeHorizontalBinding =
        FragmentHomeHorizontalBinding.bind(itemView)
    private val adapter = HorizontalMovieInfoAdapter()

    fun bind(movieList: MovieList) {
        binding.rvFilmInfo.adapter = adapter
        binding.tvFilmGroup.text = movieList.listTitle
        adapter.myData = movieList.movieList
        adapter.notifyDataSetChanged()
    }

}