package com.example.dreamcinema.presentation.genreFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo
import javax.inject.Inject

class MovieByGenreAdapter @Inject constructor(
val listener: OnMovieClickListener
): ListAdapter<MovieDetailInfo, MovieByGenreViewHolder>(MovieByGenreDiffCallBack){

    var myData: List<MovieDetailInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_by_genre_card, parent, false)
        return MovieByGenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieByGenreViewHolder, position: Int) {
      val movie = myData[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listener.onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieDetailInfo: MovieDetailInfo)
    }
}