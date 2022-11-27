package com.example.dreamcinema.domain

import androidx.room.PrimaryKey
import javax.inject.Inject

data class MovieInfo @Inject constructor(
    var id: Int,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double
)