package com.example.dreamcinema.data.dataBase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.dreamcinema.data.MovieMapper
import com.example.dreamcinema.data.network.api.ApiFactory
import com.example.dreamcinema.data.network.api.ApiFactory.apiService
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val mapper: MovieMapper,
    private val movieInfoDao: MovieInfoDao
) : MovieRepository {


    override fun getMovieInfoList(): LiveData<List<MovieInfo>> {
        return Transformations.map(movieInfoDao.getMovieInfoList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getMovieDetailInfo(title: String): LiveData<MovieInfo> {
     return  Transformations.map(movieInfoDao.getMovieDetailInfo(title)){
         mapper.mapDbModelToEntity(it)
     }
    }
}