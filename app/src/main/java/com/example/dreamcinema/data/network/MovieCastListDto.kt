package com.example.dreamcinema.data.network

import com.google.gson.annotations.SerializedName

data class MovieCastListDto (

@SerializedName("id") val id : Int,
@SerializedName("cast") val cast : List<MovieCastDto>
)
