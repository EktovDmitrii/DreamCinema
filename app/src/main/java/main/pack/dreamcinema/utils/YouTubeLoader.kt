package main.pack.dreamcinema.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import main.pack.dreamcinema.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YouTubeLoader(
    val lifecycle: Lifecycle,
    val youTubePlayerView: YouTubePlayerView,
    val errorImageView: ImageView
) {

    companion object {
        private const val START_COUNTER = 0
        private const val START_SECONDS = 0f
    }

    var counter = START_COUNTER

    fun loadVideo(listUrl: List<String>) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                if (listUrl.isNotEmpty()) {
                    youTubePlayer.loadVideo(listUrl[counter], START_SECONDS)
                }
            }

            override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError,
            ) {
                super.onError(youTubePlayer, error)
                counter++
                if (counter <= listUrl.lastIndex) {
                    loadVideo(listUrl)
                } else {
                    youTubePlayerView.visibility = View.GONE
                    errorImageView.setImageResource(R.drawable.ic_null_person)
                    errorImageView.visibility = View.VISIBLE
                }
            }
        })
    }
}