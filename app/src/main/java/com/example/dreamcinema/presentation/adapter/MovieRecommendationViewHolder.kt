package com.example.dreamcinema.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamcinema.databinding.RecommendationCardBinding
import com.example.dreamcinema.domain.MovieInfo

class MovieRecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: RecommendationCardBinding = RecommendationCardBinding.bind(itemView)


    fun bind(movieInfo: MovieInfo) {
        binding.tvMovieName.text = movieInfo.title
        binding.tvRecomMovieRate.text = movieInfo.voteAverage.toString()
        Glide.with(itemView).load(BASE_URL + movieInfo.posterPath)
            .into(binding.ivRecomMovieImage)
    }


    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}