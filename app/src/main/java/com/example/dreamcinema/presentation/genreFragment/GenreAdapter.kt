package com.example.dreamcinema.presentation.genreFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.Genre
import javax.inject.Inject

class GenreAdapter @Inject constructor(
):ListAdapter<Genre, GenreViewHolder>(MovieGenreDiffCallback) {

    var myData: List<Genre> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.genre_card, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = myData[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}