package com.example.dreamcinema.domain

import androidx.lifecycle.LiveData
import com.example.dreamcinema.data.network.MovieInfoDto

interface MovieRepository {

    suspend fun getTopMovieInfoList(): List<MovieInfo>

    suspend fun getPopularMovieInfoList(): List<MovieInfo>

    suspend fun getNowPlayingMovieInfoList(): List<MovieInfo>

    suspend fun getUpcomingMovieInfoList(): List<MovieInfo>

    suspend fun getLatestMovieInfoList(): List<MovieInfo>

    suspend fun getAllMovieListsInfo(): List<MovieList>

    fun getMovieDetailInfo(title: String): MovieInfo
}