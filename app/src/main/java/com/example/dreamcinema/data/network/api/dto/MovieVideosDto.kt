package com.example.dreamcinema.data.network.api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieVideosDto (
    @SerializedName("id") val id : String,
    @SerializedName("key") val key : String?

): Parcelable