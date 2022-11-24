package com.example.dreamcinema.domain

import javax.inject.Inject

class GetMovieInfoListUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke() = repository.getMovieInfoList()
}