package com.example.dreamcinema.presentation.homeFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.databinding.FragmentHomeHorizontalBinding
import com.example.dreamcinema.domain.MovieList

class VerticalMovieInfoViewHolder(
    itemView: View,
    listener: HorizontalMovieInfoAdapter.OnMovieClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: FragmentHomeHorizontalBinding =
        FragmentHomeHorizontalBinding.bind(itemView)
    private val adapter = HorizontalMovieInfoAdapter(listener)

    fun bind(movieList: MovieList) {
        binding.rvFilmInfo.adapter = adapter
        binding.tvFilmGroup.text = movieList.listTitle
        adapter.myData = movieList.movieList
    }

}