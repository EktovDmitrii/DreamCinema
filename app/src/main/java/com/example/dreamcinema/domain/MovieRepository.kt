package com.example.dreamcinema.domain

import androidx.lifecycle.LiveData
import com.example.dreamcinema.data.network.MovieInfoDto

interface MovieRepository {

   suspend  fun getMovieInfoList():  List<MovieInfo>

    fun getMovieDetailInfo(title: String): MovieInfo
}