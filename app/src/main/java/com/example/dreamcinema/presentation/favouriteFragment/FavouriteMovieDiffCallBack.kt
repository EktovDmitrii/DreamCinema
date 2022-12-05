package com.example.dreamcinema.presentation.favouriteFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieInfo

object FavouriteMovieDiffCallBack : DiffUtil.ItemCallback<MovieInfo>(){

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}