package main.pack.dreamcinema.domain.useCases

import main.pack.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class GetRecommendedMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(
        movieId: Int
    ) = repository.getRecommendedMoviesList(movieId)
}