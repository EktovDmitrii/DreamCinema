package main.pack.dreamcinema.domain.useCases

import main.pack.dreamcinema.domain.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) {
     operator fun invoke() = repository.getMovieList()
}