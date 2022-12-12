package com.example.dreamcinema.presentation.genreFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.Genre
import com.example.dreamcinema.presentation.CourseRvModel
import javax.inject.Inject

class GenreAdapter @Inject constructor(
    private val courseList: ArrayList<CourseRvModel>,
    private val context: Context?,
    val listener: OnGenreClickListener
) : ListAdapter<Genre, GenreViewHolder>(MovieGenreDiffCallback) {

    var myData: List<Genre> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.genre_card, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = myData[position]
        holder.bind(genre)
        holder.itemView.setOnClickListener {
            listener.onGenreClick(genre)
        }
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    interface OnGenreClickListener {
        fun onGenreClick(genre: Genre)
    }
}