package com.example.dreamcinema.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_overview")
data class MovieInfoDbModel(

    val adult: Boolean,
    val backdropPath: String,
    val genreIds: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    @PrimaryKey
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
