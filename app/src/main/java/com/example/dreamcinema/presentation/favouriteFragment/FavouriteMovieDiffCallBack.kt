package com.example.dreamcinema.presentation.favouriteFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo

object FavouriteMovieDiffCallBack : DiffUtil.ItemCallback<MovieDetailInfo>(){

    override fun areItemsTheSame(oldItem: MovieDetailInfo, newItem: MovieDetailInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetailInfo, newItem: MovieDetailInfo): Boolean {
        return oldItem == newItem
    }
}