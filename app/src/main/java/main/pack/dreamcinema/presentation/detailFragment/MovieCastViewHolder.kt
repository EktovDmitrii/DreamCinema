package main.pack.dreamcinema.presentation.detailFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.CastCardBinding
import main.pack.dreamcinema.domain.MovieCast

class MovieCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: CastCardBinding = CastCardBinding.bind(itemView)

    fun bind(movieCast: MovieCast) {
        binding.tvActorName.text = movieCast.name
        binding.tvCharacter.text = movieCast.character
        if (movieCast.profilePath != null) {
            Glide.with(itemView).load(BASE_URL + movieCast.profilePath)
                .into(binding.ivActorImage)
        } else {
            Glide.with(itemView).load(R.drawable.ic_null_person)
                .into(binding.ivActorImage)
        }
    }

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}