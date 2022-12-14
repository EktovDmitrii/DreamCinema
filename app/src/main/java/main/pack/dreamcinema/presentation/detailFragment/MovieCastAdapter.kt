package main.pack.dreamcinema.presentation.detailFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import main.pack.dreamcinema.R
import main.pack.dreamcinema.domain.MovieCast
import javax.inject.Inject

class MovieCastAdapter @Inject constructor(
) : ListAdapter<MovieCast, MovieCastViewHolder>(MovieCastDiffCallback) {

    var myData: List<MovieCast> = listOf()

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