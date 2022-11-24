package com.example.dreamcinema.di

import androidx.lifecycle.ViewModel
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
}