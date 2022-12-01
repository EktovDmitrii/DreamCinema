package com.example.dreamcinema.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieCast
import com.example.dreamcinema.domain.MovieInfo
import javax.inject.Inject

class MovieRecommendationAdapter @Inject constructor(
    val listener: OnMovieClickListener
) : RecyclerView.Adapter<MovieRecommendationViewHolder>() {

    var myData: List<MovieInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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