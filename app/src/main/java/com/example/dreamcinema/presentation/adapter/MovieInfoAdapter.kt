package com.example.dreamcinema.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dreamcinema.databinding.MovieInfoBinding
import com.example.dreamcinema.domain.MovieInfo
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieInfoAdapter @Inject constructor(
    private val context: Context
) : ListAdapter<MovieInfo, MovieInfoViewHolder>(MovieInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieInfoViewHolder {
        val binding = MovieInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieInfoViewHolder, position: Int) {
        val movie = getItem(position)
  holder.binding.tvFilmName.text = movie.title
    holder.binding.tvFilmRate.text = movie.voteAverage.toString()
Picasso.get().load(movie.posterPath).into(holder.binding.ivFilmLogo)
    }
}