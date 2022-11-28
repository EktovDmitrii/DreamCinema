package com.example.dreamcinema.di

import android.app.Application
import com.example.dreamcinema.presentation.HomeFragment
import com.example.dreamcinema.presentation.MainActivity
import com.example.dreamcinema.presentation.MovieApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(application: MovieApp)

    fun inject(fragment: HomeFragment)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}