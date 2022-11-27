package com.example.dreamcinema.domain

import javax.inject.Inject

class GetMovieInfoListUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getMovieInfoList()
}