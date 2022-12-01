package com.example.dreamcinema.presentation.homeFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamcinema.databinding.MovieInfoBinding
import com.example.dreamcinema.domain.MovieInfo

class HorizontalMovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    val binding: MovieInfoBinding = MovieInfoBinding.bind(itemView)
    fun bind(movieInfo: MovieInfo) {
        binding.tvFilmName.text = movieInfo.title
        binding.tvFilmRate.text = movieInfo.voteAverage.toString()
        Glide.with(itemView).load(BASE_URL + movieInfo.posterPath)
            .into(binding.ivFilmLogo)

    }


    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}