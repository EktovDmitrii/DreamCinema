package com.example.dreamcinema.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.CastCardBinding
import com.example.dreamcinema.domain.MovieCast
import com.squareup.picasso.Picasso

class MovieCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: CastCardBinding = CastCardBinding.bind(itemView)


    fun bind(movieCast: MovieCast) {
        binding.tvActorName.text = movieCast.name
        binding.tvActorInfo.text = movieCast.character
        if (movieCast.profilePath != null) {
            Picasso.get().load(BASE_URL + movieCast.profilePath)
                .into(binding.ivActorImage)
        } else {
            Picasso.get().load(R.drawable.ic_launcher_background)
                .into(binding.ivActorImage)
        }
    }


    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}