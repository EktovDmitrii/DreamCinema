package com.example.dreamcinema.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieInfo

object MovieInfoDiffCallback: DiffUtil.ItemCallback<MovieInfo>() {

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}