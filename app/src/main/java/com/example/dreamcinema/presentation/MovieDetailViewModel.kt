package com.example.dreamcinema.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.useCases.GetAllMovieInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getAllMovieInfoUseCase: GetAllMovieInfoUseCase
): ViewModel() {

    private val _movie = MutableLiveData<MovieInfo>()
    val movie: LiveData<MovieInfo> = _movie

    fun getAllMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetail = getAllMovieInfoUseCase()
            withContext((Dispatchers.Main)) {
                _movie.value = movieDetail
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}