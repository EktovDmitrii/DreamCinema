package main.pack.dreamcinema.data.network.api.dto

import com.google.gson.annotations.SerializedName

data class GenreListDto(
    @SerializedName("genres") val genres: List<GenreDto>

)
