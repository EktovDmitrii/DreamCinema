package com.example.dreamcinema.domain.useCases

import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieDetailInfo: MovieDetailInfo
    ) = repository.addMovie(movieDetailInfo)
}