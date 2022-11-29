package com.example.dreamcinema.di

import androidx.lifecycle.ViewModel
import com.example.dreamcinema.presentation.FavouriteViewModel
import com.example.dreamcinema.presentation.GenreViewModel
import com.example.dreamcinema.presentation.MovieInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @ViewModelKey(MovieInfoViewModel::class)
    @IntoMap
    fun bindViewModel(viewModel: MovieInfoViewModel): ViewModel

    @Binds
    @ViewModelKey(GenreViewModel::class)
    @IntoMap
    fun bindGenreViewModel(viewModel: GenreViewModel): ViewModel

    @Binds
    @ViewModelKey(FavouriteViewModel::class)
    @IntoMap
    fun bindFavouriteViewModel(viewModel: FavouriteViewModel): ViewModel
}