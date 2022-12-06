package com.example.dreamcinema.presentation.genreFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.Genre
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.useCases.GetGenreUseCase
import com.example.dreamcinema.domain.useCases.GetMoviesByGenreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val getGenreUseCase: GetGenreUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
): ViewModel() {

    private val _genre = MutableLiveData<List<Genre>>()
    val genre: LiveData<List<Genre>>
    get() = _genre

    private val _movie = MutableLiveData<List<MovieDetailInfo>>()
    val movie: LiveData<List<MovieDetailInfo>>
    get() = _movie

    fun getMoviesByGenre(genreId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val movielist = getMoviesByGenreUseCase(genreId)
            withContext(Dispatchers.Main){
                _movie.value = movielist
            }
        }
    }

    fun getGenreInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            val movieGenre = getGenreUseCase()
            withContext(Dispatchers.Main){
                _genre.value = movieGenre
            }
        }
    }
}