package main.pack.dreamcinema.domain.useCases

import main.pack.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class GetUpcomingMovieInfoUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getUpcomingMovieInfoList()
}