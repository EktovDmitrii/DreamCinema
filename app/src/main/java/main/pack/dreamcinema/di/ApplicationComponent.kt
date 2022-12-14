package main.pack.dreamcinema.di

import android.app.Application
import main.pack.dreamcinema.presentation.MainActivity
import main.pack.dreamcinema.presentation.MovieApp
import main.pack.dreamcinema.presentation.detailFragment.MovieDetailFragment
import main.pack.dreamcinema.presentation.favouriteFragment.FavouriteFragment
import main.pack.dreamcinema.presentation.genreFragment.GenreFragment
import main.pack.dreamcinema.presentation.genreFragment.MoviesByGenreFragment
import main.pack.dreamcinema.presentation.homeFragment.HomeFragment
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