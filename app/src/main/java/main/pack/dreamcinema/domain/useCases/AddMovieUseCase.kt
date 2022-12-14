package main.pack.dreamcinema.domain.useCases

import main.pack.dreamcinema.domain.MovieDetailInfo
import main.pack.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(
        movieDetailInfo: MovieDetailInfo
    ) = repository.addMovie(movieDetailInfo)
}