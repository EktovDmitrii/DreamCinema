package com.example.dreamcinema.domain.useCases

import com.example.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class GetTopMovieInfoListUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getTopMovieInfoList()
}
