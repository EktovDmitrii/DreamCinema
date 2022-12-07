package com.example.dreamcinema.utils

import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.lang.RuntimeException

class YouTubeLoader(
val lifecycle: Lifecycle,
val youTubePlayerView: YouTubePlayerView
){
    fun loadVideo(url: String?){
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.getPlayerUiController()
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                if (url != null) {
                    youTubePlayer.loadVideo(url, 0f)
                } else{
                    throw RuntimeException("Can't find video")
                }
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
            }
        })
    }
}