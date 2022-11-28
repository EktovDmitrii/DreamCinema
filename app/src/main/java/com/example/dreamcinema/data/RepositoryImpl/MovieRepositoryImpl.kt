package com.example.dreamcinema.data.RepositoryImpl

import com.example.dreamcinema.data.network.api.ApiService
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override suspend fun getTopMovieInfoList(): List<MovieInfo> {
        return apiService.getTopMoviesInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage
            )
        }
    }


    override fun getMovieDetailInfo(title: String): MovieInfo {
        TODO("Not yet implemented")
    }
}