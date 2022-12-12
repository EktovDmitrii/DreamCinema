package com.example.dreamcinema.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class MovieList @Inject constructor(
    val listTitle: String,
    val movieList: List<MovieInfo>
) : Parcelable