package com.example.dreamcinema.presentation.genreFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieDetailInfo
import javax.inject.Inject

class MovieByGenreAdapter @Inject constructor(
    val listener: OnMovieClickListener
) : PagingDataAdapter<MovieDetailInfo, MovieByGenreViewHolder>(MovieByGenreDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_by_genre_card, parent, false)
        return MovieByGenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieByGenreViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                listener.onMovieClick(movie)
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieDetailInfo: MovieDetailInfo)
    }
}