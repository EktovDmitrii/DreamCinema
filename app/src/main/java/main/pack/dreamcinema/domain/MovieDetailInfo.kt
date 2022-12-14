package main.pack.dreamcinema.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class MovieDetailInfo @Inject constructor(
    var id: Int,
    var posterPath: String?,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
    val video: Boolean,
    val overview: String,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    var isInFavourite: Boolean
) : Parcelable
