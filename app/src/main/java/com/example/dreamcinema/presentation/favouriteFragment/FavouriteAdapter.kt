package com.example.dreamcinema.presentation.favouriteFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dreamcinema.R
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.presentation.CourseRvModel
import javax.inject.Inject

class FavouriteAdapter @Inject constructor(
    private val courseList: ArrayList<CourseRvModel>,
    private val context: Context?
    ): ListAdapter<MovieInfo, FavouriteViewHolder>(FavouriteMovieDiffCallBack) {

        var myData: List<MovieInfo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.movie_info, parent, false)
        return FavouriteViewHolder(view
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val movie = myData[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
       return myData.size
    }
}