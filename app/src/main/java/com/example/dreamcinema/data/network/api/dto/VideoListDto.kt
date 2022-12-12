package com.example.dreamcinema.data.network.api.dto

import com.google.gson.annotations.SerializedName

data class VideoListDto(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<MovieVideosDto>
)