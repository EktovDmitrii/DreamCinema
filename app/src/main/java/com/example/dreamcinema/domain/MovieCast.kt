package com.example.dreamcinema.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String?,
    val name: String?,
    val popularity: Double,
    val profilePath: String?,
    val castId: Int,
    val character: String?,
    val creditId: String?,
    val order: Int
) : Parcelable
