package com.example.dreamcinema.data.network.api

import com.example.dreamcinema.data.network.MovieInfoListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/top_rated")
    suspend fun getTopMoviesInfo(
        @Query(QUERY_PARAM_API_KEY) api_key: String = "139c985f543aaf9db3e818b31275a4c1",
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE
    ): MovieInfoListDto

    companion object {

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val LANGUAGE = "eng-ENG"

    }
}