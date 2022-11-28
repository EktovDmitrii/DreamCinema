package com.example.dreamcinema.domain.useCases

import com.example.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class GetMovieDetailInfoUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(title: String) = repository.getMovieDetailInfo(title)
}