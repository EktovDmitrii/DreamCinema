package com.example.dreamcinema.di

import com.example.dreamcinema.data.repositoryImpl.MovieRepositoryImpl
import com.example.dreamcinema.data.network.api.ApiFactory
import com.example.dreamcinema.data.network.api.ApiService
import com.example.dreamcinema.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {


    @Binds
    @ApplicationScope
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {


        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }
}