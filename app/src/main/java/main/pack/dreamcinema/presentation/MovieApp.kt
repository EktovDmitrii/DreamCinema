package main.pack.dreamcinema.presentation

import android.app.Application
import leakcanary.LeakCanary

class MovieApp : Application() {

    val component by lazy {
        main.pack.dreamcinema.di.DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}