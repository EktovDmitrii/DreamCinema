package com.example.dreamcinema.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieCast
import javax.inject.Inject

class MovieCastAdapter @Inject constructor(): RecyclerView.Adapter<MovieCastViewHolder>(){

    var myData: List<MovieCast> = listOf()
    set(value)  {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cast_card, parent, false)
        return MovieCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
val actors = myData[position]
        holder.bind(actors)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}