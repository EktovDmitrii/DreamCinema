package com.example.dreamcinema.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieList
import javax.inject.Inject

class VerticalMovieInfoAdapter @Inject constructor(
) : RecyclerView.Adapter<VerticalMovieInfoViewHolder>() {

    var myData: List<MovieList> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalMovieInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_horizontal, parent, false)
        return VerticalMovieInfoViewHolder(view)
    }

    override fun onBindViewHolder(holderVertical: VerticalMovieInfoViewHolder, position: Int) {
        val movie = myData[position]
        holderVertical.bind(movie)

    }

    override fun getItemCount(): Int {
        return myData.size
    }
}