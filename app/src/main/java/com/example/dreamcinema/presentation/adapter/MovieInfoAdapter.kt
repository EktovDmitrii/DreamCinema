package com.example.dreamcinema.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.R
import com.example.dreamcinema.data.network.MovieInfoDto
import com.example.dreamcinema.databinding.MovieInfoBinding
import com.example.dreamcinema.domain.MovieInfo
import javax.inject.Inject

class MovieInfoAdapter @Inject constructor(
) : RecyclerView.Adapter<MovieInfoViewHolder>() {

    var myData: List<MovieInfoDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_info, parent, false)
        return MovieInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieInfoViewHolder, position: Int) {
        val movie = myData[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}