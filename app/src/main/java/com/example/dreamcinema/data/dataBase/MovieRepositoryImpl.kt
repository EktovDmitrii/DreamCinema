package com.example.dreamcinema.data.dataBase

import com.example.dreamcinema.data.network.api.RemoteDataSource
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getMovieInfoList(): List<MovieInfo> {
        return dataSource.getTopRatedMovies().movieList.map { it ->
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