package com.example.dreamcinema.data.dataBase

import com.example.dreamcinema.domain.MovieInfo
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapEntityToDbModel(movieInfo: MovieInfo): MovieDbModel = MovieDbModel(
        id = movieInfo.id,
        posterPath = movieInfo.posterPath,
        releaseDate = movieInfo.releaseDate,
        title = movieInfo.releaseDate,
        voteAverage = movieInfo.voteAverage,
        video = movieInfo.video,
        overview = movieInfo.overview,
        backdropPath = movieInfo.backdropPath,
        genreIds = movieInfo.genreIds
    )

    fun mapDbModelToEntity(movieDbModel: MovieDbModel): MovieInfo = MovieInfo(
        id = movieDbModel.id,
        posterPath = movieDbModel.posterPath,
        releaseDate = movieDbModel.releaseDate,
        title = movieDbModel.title,
        voteAverage = movieDbModel.voteAverage,
        video = movieDbModel.video,
        overview = movieDbModel.overview,
        backdropPath = movieDbModel.backdropPath,
        genreIds = movieDbModel.genreIds
    )

    fun mapListDbModelToEntity(list: List<MovieDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}