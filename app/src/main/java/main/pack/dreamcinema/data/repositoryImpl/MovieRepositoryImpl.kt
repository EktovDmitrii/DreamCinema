package main.pack.dreamcinema.data.repositoryImpl

import main.pack.dreamcinema.data.dataBase.Mapper
import main.pack.dreamcinema.data.dataBase.MovieDao
import main.pack.dreamcinema.data.network.api.ApiService
import main.pack.dreamcinema.domain.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getTopMovieInfoList(): List<MovieInfo> {
        return apiService.getTopMoviesInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
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

    override suspend fun getDetails(movieId: Int): MovieDetailInfo {
        val favouriteMovie = movieDao.getMovieList()
        val isInFavourite = favouriteMovie.find { it.id == movieId } != null
        with(apiService.getDetails(movieId)) {
            return MovieDetailInfo(
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                video = video,
                overview = overview,
                backdropPath = backdropPath,
                genreIds = genreIds,
                isInFavourite = isInFavourite
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

    override suspend fun getMoviesByGenre(page: Int, genreId: Int): List<MovieDetailInfo> {
        return apiService.getMoviesByGenre(page, genreId).movieList.map {
            MovieDetailInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                video = it.video,
                overview = it.overview,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                isInFavourite = false
            )
        }
    }

    override suspend fun getVideos(movieId: Int): List<MovieVideos> {
        return apiService.getVideos(movieId).results.map {
            MovieVideos(
                id = it.id,
                key = it.key
            )
        }
    }

    override suspend fun addMovie(movieDetailInfo: MovieDetailInfo) {
        movieDao.addMovie(mapper.mapEntityToDbModel(movieDetailInfo))
    }

    override fun getMovieList(): List<MovieDetailInfo> {
        val dbModel = movieDao.getMovieList()
        return mapper.mapListDbModelToEntity(dbModel)
    }

    override suspend fun deleteMovie(movieDetailInfo: MovieDetailInfo) {
        movieDao.deleteMovie(movieDetailInfo.id)
    }
}