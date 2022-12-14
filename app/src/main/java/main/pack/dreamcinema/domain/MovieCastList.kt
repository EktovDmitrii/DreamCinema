package main.pack.dreamcinema.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCastList(
    val id: Int,
    val cast: List<MovieCast>
) : Parcelable
