package com.example.dreamcinema.presentation.genreFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.GenreCardBinding
import com.example.dreamcinema.domain.Genre

class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = GenreCardBinding.bind(itemView)

    fun bind(genre: Genre) {
        binding.tvGenreName.text = genre.name
        setGenreImage(genre)
    }

    private fun setGenreImage(genre: Genre) {
        if (genre.name == "Action")
            Glide.with(itemView).load(R.mipmap.ic_channel_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Adventure")
            Glide.with(itemView).load(R.mipmap.ic_adventure_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Animation")
            Glide.with(itemView).load(R.mipmap.ic_animation_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Comedy")
            Glide.with(itemView).load(R.mipmap.ic_comedy_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Crime")
            Glide.with(itemView).load(R.mipmap.ic_crime_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Documentary")
            Glide.with(itemView).load(R.mipmap.ic_documentary_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Drama")
            Glide.with(itemView).load(R.mipmap.ic_drama_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Family")
            Glide.with(itemView).load(R.mipmap.ic_family_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Fantasy")
            Glide.with(itemView).load(R.mipmap.ic_fantasy_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "History")
            Glide.with(itemView).load(R.mipmap.ic_history_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Horror")
            Glide.with(itemView).load(R.mipmap.ic_horror_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Music")
            Glide.with(itemView).load(R.mipmap.ic_music_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Mystery")
            Glide.with(itemView).load(R.mipmap.ic_mystery_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Romance")
            Glide.with(itemView).load(R.mipmap.ic_romance_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "TV Movie")
            Glide.with(itemView).load(R.mipmap.ic_tv_movie_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Thriller")
            Glide.with(itemView).load(R.mipmap.ic_thriller_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "War")
            Glide.with(itemView).load(R.mipmap.ic_war_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Science Fiction")
            Glide.with(itemView).load(R.mipmap.ic_science_fiction_foreground)
                .into(binding.ivGenreView)
        if (genre.name == "Western")
            Glide.with(itemView).load(R.mipmap.ic_western_foreground)
                .into(binding.ivGenreView)
    }
}