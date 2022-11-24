package com.example.dreamcinema.domain

import androidx.room.PrimaryKey
import javax.inject.Inject

data class MovieInfo @Inject constructor(

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