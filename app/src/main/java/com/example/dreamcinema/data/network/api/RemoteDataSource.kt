package com.example.dreamcinema.data.network.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {




    companion object {

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val LANGUAGE = "eng-ENG"

    }

//    fun getNowPlayingMovieList(): Single<ResponseResult> {
//        return cinemaApi.getMovieListNowPlaying(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
//    }
}