package main.pack.dreamcinema.data.dataBase

import main.pack.dreamcinema.domain.MovieDetailInfo
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapEntityToDbModel(movieDetailInfo: MovieDetailInfo): MovieDbModel = MovieDbModel(
        id = movieDetailInfo.id,
        posterPath = movieDetailInfo.posterPath,
        releaseDate = movieDetailInfo.releaseDate,
        title = movieDetailInfo.title,
        voteAverage = movieDetailInfo.voteAverage,
        video = movieDetailInfo.video,
        overview = movieDetailInfo.overview,
        backdropPath = movieDetailInfo.backdropPath,
        genreIds = movieDetailInfo.genreIds
    )

    private fun mapDbModelToEntity(movieDbModel: MovieDbModel): MovieDetailInfo = MovieDetailInfo(
        id = movieDbModel.id,
        posterPath = movieDbModel.posterPath,
        releaseDate = movieDbModel.releaseDate,
        title = movieDbModel.title,
        voteAverage = movieDbModel.voteAverage,
        video = movieDbModel.video,
        overview = movieDbModel.overview,
        backdropPath = movieDbModel.backdropPath,
        genreIds = movieDbModel.genreIds,
        isInFavourite = false
    )

    fun mapListDbModelToEntity(list: List<MovieDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}