package com.example.dreamcinema.presentation.detailFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieCast

object MovieCastDiffCallback: DiffUtil.ItemCallback<MovieCast> (){

    override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem == newItem
    }
}