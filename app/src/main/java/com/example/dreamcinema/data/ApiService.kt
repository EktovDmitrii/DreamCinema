package com.example.dreamcinema.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopMoviesInfo(

        @Query(QUERY_PARAM_API_KEY) apiKey: String = "139c985f543aaf9db3e818b31275a4c1",

        ): MovieInfoListDto


    companion object {

        private const val QUERY_PARAM_API_KEY = "api_key"
    }
}