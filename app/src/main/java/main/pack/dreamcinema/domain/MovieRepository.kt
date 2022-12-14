package main.pack.dreamcinema.domain

interface MovieRepository {

    suspend fun getTopMovieInfoList(): List<MovieInfo>

    suspend fun getPopularMovieInfoList(): List<MovieInfo>

    suspend fun getNowPlayingMovieInfoList(): List<MovieInfo>

    suspend fun getUpcomingMovieInfoList(): List<MovieInfo>

    suspend fun getAllMovieListsInfo(): List<MovieList>

    suspend fun getDetails(movieId: Int): MovieDetailInfo

    suspend fun getMovieCast(movieId: Int): List<MovieCast>

    suspend fun getRecommendedMoviesList(movieId: Int): List<MovieInfo>

    suspend fun getGenreInfo(): List<Genre>

    suspend fun getMoviesByGenre(page: Int, genreId: Int): List<MovieDetailInfo>

    suspend fun addMovie(movieDetailInfo: MovieDetailInfo)

    fun getMovieList(): List<MovieDetailInfo>

    suspend fun deleteMovie(movieDetailInfo: MovieDetailInfo)

    suspend fun getVideos(movieId: Int): List<MovieVideos>
}
