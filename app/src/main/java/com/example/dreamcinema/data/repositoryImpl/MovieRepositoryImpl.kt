package com.example.dreamcinema.data.repositoryImpl

import com.example.dreamcinema.data.network.api.ApiService
import com.example.dreamcinema.domain.*
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
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getPopularMovieInfoList(): List<MovieInfo> {
        return apiService.getPopularMoviesInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getNowPlayingMovieInfoList(): List<MovieInfo> {
        return apiService.getNowPlayingMovieInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getUpcomingMovieInfoList(): List<MovieInfo> {
        return apiService.getUpcomingMovieInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds
            )
        }
    }


    override suspend fun getAllMovieListsInfo(): List<MovieList> {
        val getTop = MovieList("Top rated movies", getTopMovieInfoList())
        val getUpcoming = MovieList("Upcoming movies", getUpcomingMovieInfoList())
        val getPopular = MovieList("Popular movies", getPopularMovieInfoList())
        val getNowPlaying = MovieList("Now playing movies", getNowPlayingMovieInfoList())
        return listOf(getNowPlaying, getPopular, getTop, getUpcoming)
    }

    override suspend fun getDetails(movieId: Int): MovieInfo {
        with(apiService.getDetails(movieId)) {
            return MovieInfo(
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                video = video,
                overview = overview,
                popularity = popularity,
                backdropPath = backdropPath,
                genreIds = genreIds
            )
        }
    }

    override suspend fun getMovieCast(movieId: Int): List<MovieCast> {
        val result = apiService.getMovieCast(movieId).cast.map {
            MovieCast(
                adult = it.adult,
                gender = it.gender,
                id = it.id,
                knownForDepartment = it.knownForDepartment,
                name = it.name,
                popularity = it.popularity,
                profilePath = it.profilePath,
                castId = it.castId,
                character = it.character,
                creditId = it.creditId,
                order = it.order
            )
        }
        return result
    }

    override suspend fun getRecommendedMoviesList(movieId: Int): List<MovieInfo> {
        return apiService.getRecommendedMovies(movieId).movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getGenreInfo(): List<Genre> {
        return apiService.getGenre().genres.map {
            Genre(
                id = it.id,
                name = it.name
            )
        }
    }

    override suspend fun getMoviesByGenre(genreId: Int): List<MovieInfo> {
        return apiService.getMoviesByGenre(genreId).movieList.map {
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds
            )
        }
    }
}