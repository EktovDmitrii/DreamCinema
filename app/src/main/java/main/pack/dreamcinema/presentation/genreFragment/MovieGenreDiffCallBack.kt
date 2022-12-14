package main.pack.dreamcinema.presentation.genreFragment

import androidx.recyclerview.widget.DiffUtil
import main.pack.dreamcinema.domain.Genre

object MovieGenreDiffCallback : DiffUtil.ItemCallback<Genre>() {

    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}