package com.example.dreamcinema.domain.useCases

import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieInfo: MovieInfo) = repository.deleteMovie(movieInfo)
}