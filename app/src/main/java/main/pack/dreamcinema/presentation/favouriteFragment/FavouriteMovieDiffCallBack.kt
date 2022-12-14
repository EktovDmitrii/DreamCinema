package main.pack.dreamcinema.presentation.favouriteFragment

import androidx.recyclerview.widget.DiffUtil
import main.pack.dreamcinema.domain.MovieDetailInfo

object FavouriteMovieDiffCallBack : DiffUtil.ItemCallback<MovieDetailInfo>() {

    override fun areItemsTheSame(oldItem: MovieDetailInfo, newItem: MovieDetailInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetailInfo, newItem: MovieDetailInfo): Boolean {
        return oldItem == newItem
    }
}