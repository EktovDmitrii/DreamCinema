package com.example.dreamcinema.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class MovieInfo @Inject constructor(
    var id: Int,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
    val video : Boolean,
    val overview : String,
    val popularity : Double,
    val backdropPath : String?,
    val genreIds : List<Int>,
)