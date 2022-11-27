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
    fun bind(movieInfoDto: MovieInfoDto) {
        binding.tvFilmName.text = movieInfoDto.title
        binding.tvFilmRate.text = movieInfoDto.voteAverage.toString()
    }
}