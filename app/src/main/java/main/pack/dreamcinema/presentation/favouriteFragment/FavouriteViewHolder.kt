package main.pack.dreamcinema.presentation.favouriteFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.MovieFavouriteInfoBinding
import main.pack.dreamcinema.domain.MovieDetailInfo

class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = MovieFavouriteInfoBinding.bind(itemView)

    fun bind(movieDetailInfo: MovieDetailInfo) {
        with(movieDetailInfo) {
            binding.tvFilmName.text = title
            binding.tvFilmRate.text = voteAverage.toString()
            if (posterPath != null) {
                Glide.with(itemView).load(BASE_URL + posterPath)
                    .into(binding.ivFilmLogo)
            } else {
                Glide.with(itemView).load(R.drawable.ic_baseline_image_not_supported_24)
                    .into(binding.ivFilmLogo)
            }
        }
    }

    companion object {

        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"

    }
}