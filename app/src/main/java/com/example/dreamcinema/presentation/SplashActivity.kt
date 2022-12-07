package com.example.dreamcinema.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import com.example.dreamcinema.R


class SplashActivity : Activity() {
    companion object {
        private const val SPLASH_DISPLAY_LENGTH = 3000L
    }

    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}