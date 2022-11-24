package com.example.dreamcinema.domain

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getMovieInfoList(): LiveData<List<MovieInfo>>

    fun getMovieDetailInfo(title: String): LiveData<MovieInfo>
}