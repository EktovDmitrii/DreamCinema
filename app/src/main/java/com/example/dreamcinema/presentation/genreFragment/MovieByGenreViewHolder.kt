package com.example.dreamcinema.presentation.genreFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamcinema.databinding.MovieByGenreCardBinding
import com.example.dreamcinema.domain.MovieInfo

class MovieByGenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val binding = MovieByGenreCardBinding.bind(itemView)

    fun bind(movieInfo: MovieInfo){
        binding.tvMovieName.text = movieInfo.title
        binding.tvMovieRate.text = movieInfo.voteAverage.toString()
        Glide.with(itemView).load(BASE_POSTER_URL + movieInfo.posterPath)
            .into(binding.ivMoviePoster)
    }

    companion object{
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
    }
}