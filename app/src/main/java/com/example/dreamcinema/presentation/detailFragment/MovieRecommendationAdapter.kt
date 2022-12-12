package com.example.dreamcinema.presentation.detailFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieInfo
import javax.inject.Inject

class MovieRecommendationAdapter @Inject constructor(
    val listener: OnMovieClickListener
) : ListAdapter<MovieInfo, MovieRecommendationViewHolder>(MovieRecommendedDiffCallback) {

    var myData: List<MovieInfo> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieRecommendationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recommendation_card, parent, false)
        return MovieRecommendationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieRecommendationViewHolder, position: Int) {
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
        fun onMovieClick(movieInfo: MovieInfo)
    }
}