package main.pack.dreamcinema.domain.useCases

import main.pack.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int, genreId: Int) =
        repository.getMoviesByGenre(page, genreId)
}