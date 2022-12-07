package com.example.dreamcinema.presentation.genreFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.MovieByGenreCardBinding
import com.example.dreamcinema.domain.MovieDetailInfo

class MovieByGenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val binding = MovieByGenreCardBinding.bind(itemView)

    fun bind(movieDetailInfo: MovieDetailInfo){
        binding.tvMovieName.text = movieDetailInfo.title
        binding.tvMovieRate.text = movieDetailInfo.voteAverage.toString()
        if (movieDetailInfo.backdropPath != null) {
            Glide.with(itemView).load(BASE_POSTER_URL + movieDetailInfo.backdropPath)
                .into(binding.ivMoviePoster)
        }else {
            Glide.with(itemView).load(R.drawable.ic_baseline_image_not_supported_24)
                .into(binding.ivMoviePoster)
        }
    }

    companion object{
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
    }
}