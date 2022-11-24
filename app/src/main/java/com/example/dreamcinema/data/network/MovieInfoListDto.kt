package com.example.dreamcinema.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieInfoListDto(

    @SerializedName("results")
    @Expose
    val movieList: List<MovieInfoDto>? = null
)