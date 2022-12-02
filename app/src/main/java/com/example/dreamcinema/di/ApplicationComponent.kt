package com.example.dreamcinema.di

import android.app.Application
import com.example.dreamcinema.presentation.*
import com.example.dreamcinema.presentation.detailFragment.MovieDetailFragment
import com.example.dreamcinema.presentation.favouriteFragment.FavouriteFragment
import com.example.dreamcinema.presentation.genreFragment.GenreFragment
import com.example.dreamcinema.presentation.genreFragment.MoviesByGenreFragment
import com.example.dreamcinema.presentation.homeFragment.HomeFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(application: MovieApp)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: FavouriteFragment)

    fun inject(fragment: GenreFragment)

    fun inject(fragment: MovieDetailFragment)

    fun inject(fragment: MoviesByGenreFragment)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}