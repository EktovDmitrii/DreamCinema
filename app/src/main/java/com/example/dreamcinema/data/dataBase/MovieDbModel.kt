package com.example.dreamcinema.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class MovieDbModel(
   @PrimaryKey
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val video: Boolean,
    val overview: String,
    val backdropPath: String?,
    val genreIds: List<Int>?,
)
