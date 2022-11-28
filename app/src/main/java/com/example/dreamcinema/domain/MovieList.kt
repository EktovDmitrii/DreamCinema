package com.example.dreamcinema.domain

import javax.inject.Inject

data class MovieList @Inject constructor(
    val listTitle: String,
    val movieList: List<MovieInfo>
)