package main.pack.dreamcinema.presentation.homeFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import main.pack.dreamcinema.databinding.FragmentHomeHorizontalBinding
import main.pack.dreamcinema.domain.MovieList

class VerticalMovieInfoViewHolder(
    itemView: View,
    listener: HorizontalMovieInfoAdapter.OnMovieClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: FragmentHomeHorizontalBinding =
        FragmentHomeHorizontalBinding.bind(itemView)
    private val adapter = HorizontalMovieInfoAdapter(listener)

    fun bind(movieList: MovieList) {
        binding.rvFilmInfo.adapter = adapter
        binding.tvFilmGroup.text = movieList.listTitle
        adapter.myData = movieList.movieList
    }

}