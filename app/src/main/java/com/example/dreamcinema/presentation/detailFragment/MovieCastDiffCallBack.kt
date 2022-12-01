package com.example.dreamcinema.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieCast
import com.example.dreamcinema.domain.MovieCastList
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieList

object MovieCastDiffCallback: DiffUtil.ItemCallback<MovieCast> (){

    override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem == newItem
    }
}