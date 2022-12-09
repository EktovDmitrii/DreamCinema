package com.example.dreamcinema.presentation.favouriteFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamcinema.databinding.MovieFavouriteInfoBinding
import com.example.dreamcinema.databinding.MovieInfoBinding
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo

class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = MovieFavouriteInfoBinding.bind(itemView)

    fun bind(movieDetailInfo: MovieDetailInfo) {
        binding.tvFilmName.text = movieDetailInfo.title
        binding.tvFilmRate.text = movieDetailInfo.voteAverage.toString()
        Glide.with(itemView).load(BASE_URL + movieDetailInfo.posterPath)
            .into(binding.ivFilmLogo)
    }

    companion object {

        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"

    }
}