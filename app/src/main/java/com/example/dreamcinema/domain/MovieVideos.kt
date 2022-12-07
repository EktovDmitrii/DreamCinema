package com.example.dreamcinema.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieVideos(
    val id : String,
    val key: String?
): Parcelable
