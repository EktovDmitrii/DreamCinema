package com.example.dreamcinema.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import com.example.dreamcinema.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YouTubeLoader(
    val lifecycle: Lifecycle,
    val youTubePlayerView: YouTubePlayerView,
    val errorImageView: ImageView
) {

    var counter = 0

    fun loadVideo(listUrl: List<String>) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                if (listUrl.isNotEmpty()) {
                    youTubePlayer.loadVideo(listUrl[counter], 0f)
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