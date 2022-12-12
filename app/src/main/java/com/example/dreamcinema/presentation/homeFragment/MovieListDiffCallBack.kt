package com.example.dreamcinema.presentation.homeFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieList

object MovieListDiffCallback : DiffUtil.ItemCallback<MovieList>() {

    override fun areItemsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
        return oldItem.listTitle == newItem.listTitle
    }

    override fun areContentsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
        return oldItem == newItem
    }
}