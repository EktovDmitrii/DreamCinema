package com.example.dreamcinema.data.network.api

import com.example.dreamcinema.data.network.MovieInfoDto
import com.example.dreamcinema.data.network.MovieInfoListDto
import com.example.dreamcinema.domain.MovieInfo
import java.lang.RuntimeException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

//    suspend fun getTopRatedMovies(): MovieInfoListDto {
//        try {
//            val result = apiService.getTopMoviesInfo(QUERY_PARAM_API_KEY)
//            return result
//        } catch (e: Exception) {
//            throw RuntimeException("Retrofit error")
//        }
//    }

    suspend fun getTopRatedMovies(): MovieInfoListDto {
        return apiService.getTopMoviesInfo (QUERY_PARAM_API_KEY)
    }

    companion object {

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val LANGUAGE = "eng-ENG"

    }

//    fun getNowPlayingMovieList(): Single<ResponseResult> {
//        return cinemaApi.getMovieListNowPlaying(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
//    }
}