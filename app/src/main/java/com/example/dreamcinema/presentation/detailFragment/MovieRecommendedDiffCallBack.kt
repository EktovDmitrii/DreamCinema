package com.example.dreamcinema.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieCast
import com.example.dreamcinema.domain.MovieCastList
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieList

object MovieRecommendedDiffCallback: DiffUtil.ItemCallback<MovieInfo> (){

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}