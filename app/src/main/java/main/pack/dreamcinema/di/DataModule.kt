package main.pack.dreamcinema.di

import android.app.Application
import main.pack.dreamcinema.data.dataBase.AppDatabase
import main.pack.dreamcinema.data.dataBase.MovieDao
import main.pack.dreamcinema.data.network.api.ApiFactory
import main.pack.dreamcinema.data.network.api.ApiService
import main.pack.dreamcinema.data.repositoryImpl.MovieRepositoryImpl
import main.pack.dreamcinema.domain.MovieRepository
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

        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): MovieDao {
            return AppDatabase.getInstance(application).movieDao()
        }
    }
}