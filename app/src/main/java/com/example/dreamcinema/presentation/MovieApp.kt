package com.example.dreamcinema.presentation

import android.app.Application
import com.example.dreamcinema.di.DaggerApplicationComponent

class MovieApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}