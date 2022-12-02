package com.example.dreamcinema.presentation.genreFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.databinding.GenreCardBinding
import com.example.dreamcinema.domain.Genre

class GenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = GenreCardBinding.bind(itemView)

    fun bind(genre: Genre){
        binding.tvGenreName.text = genre.name
    }
}