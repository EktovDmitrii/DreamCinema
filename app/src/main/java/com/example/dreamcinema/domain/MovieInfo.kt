package com.example.dreamcinema.domain

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class MovieInfo @Inject constructor(
    var id: Int,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
    val video : Boolean,
    val overview : String,
    val backdropPath : String?,
    val genreIds : List<Int>?,
): Parcelable