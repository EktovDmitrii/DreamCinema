package com.example.dreamcinema.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.data.network.MovieInfoDto
import com.example.dreamcinema.databinding.MovieInfoBinding
import com.example.dreamcinema.domain.MovieInfo
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: MovieInfoBinding = MovieInfoBinding.bind(itemView)
    fun bind(movieInfo: MovieInfo) {
        binding.tvFilmName.text = movieInfo.title
        binding.tvFilmRate.text = movieInfo.voteAverage.toString()
        Picasso.get().load("https://image.tmdb.org/t/p/original/${movieInfo.posterPath}")
            .into(binding.ivFilmLogo)
    }
}