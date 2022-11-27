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
        Picasso.get().load(BASE_URL + movieInfo.posterPath)
            .into(binding.ivFilmLogo)
    }

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}