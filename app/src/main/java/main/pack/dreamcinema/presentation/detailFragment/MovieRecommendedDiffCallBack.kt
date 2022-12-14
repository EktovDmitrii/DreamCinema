package main.pack.dreamcinema.presentation.detailFragment

import androidx.recyclerview.widget.DiffUtil
import main.pack.dreamcinema.domain.MovieInfo

object MovieRecommendedDiffCallback : DiffUtil.ItemCallback<MovieInfo>() {

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}