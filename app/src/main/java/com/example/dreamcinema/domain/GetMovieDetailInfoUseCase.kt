package com.example.dreamcinema.domain

import javax.inject.Inject

class GetMovieDetailInfoUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(title: String) = repository.getMovieDetailInfo(title)
}