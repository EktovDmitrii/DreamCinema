package com.example.dreamcinema.data

import androidx.room.PrimaryKey
import com.example.dreamcinema.data.dataBase.MovieInfoDbModel
import com.example.dreamcinema.data.network.MovieInfoDto
import com.example.dreamcinema.data.network.MovieInfoListDto
import com.example.dreamcinema.domain.MovieInfo
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapDbModelToEntity(movieInfoDbModel: MovieInfoDbModel): MovieInfo = MovieInfo(
        adult = movieInfoDbModel.adult,
        backdropPath = movieInfoDbModel.backdropPath,
        genreIds = movieInfoDbModel.genreIds,
        id = movieInfoDbModel.id,
        originalLanguage = movieInfoDbModel.originalLanguage,
        originalTitle = movieInfoDbModel.originalTitle,
        overview = movieInfoDbModel.overview,
        popularity = movieInfoDbModel.popularity,
        posterPath = movieInfoDbModel.posterPath,
        releaseDate = movieInfoDbModel.releaseDate,
        title = movieInfoDbModel.originalTitle,
        video = movieInfoDbModel.video,
        voteAverage = movieInfoDbModel.voteAverage,
        voteCount = movieInfoDbModel.voteCount
    )

    fun mapDtoToDbModel(dto: MovieInfoDto) = MovieInfoDbModel(
        adult = dto.adult,
        backdropPath = dto.backdropPath,
        genreIds = dto.genreIds,
        id = dto.id,
        originalLanguage = dto.originalLanguage,
        originalTitle = dto.originalTitle,
        overview = dto.overview,
        popularity = dto.popularity,
        posterPath = dto.posterPath,
        releaseDate = dto.releaseDate,
        title = dto.originalTitle,
        video = dto.video,
        voteAverage = dto.voteAverage,
        voteCount = dto.voteCount
    )

    fun mapMovieInfoListDtoToString(movieInfoListDto: MovieInfoListDto): String {
        return movieInfoListDto.movieList?.map {
            it.title
        }?.joinToString { "," } ?: ""
    }
}
