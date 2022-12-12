package com.example.dreamcinema.presentation.genreFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.dreamcinema.domain.MovieDetailInfo

object MovieByGenreDiffCallBack : DiffUtil.ItemCallback<MovieDetailInfo>() {

    override fun areItemsTheSame(oldItem: MovieDetailInfo, newItem: MovieDetailInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetailInfo, newItem: MovieDetailInfo): Boolean {
        return oldItem == newItem
    }
}