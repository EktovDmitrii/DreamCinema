package com.example.dreamcinema.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.useCases.GetMovieDetailInfoUseCase
import com.example.dreamcinema.domain.GetTopMovieInfoListUseCase
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieList
import com.example.dreamcinema.domain.useCases.GetAllMovieInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
    private val getAllMovieInfoUseCase: GetAllMovieInfoUseCase,
    private val getMovieDetailInfoUseCase: GetMovieDetailInfoUseCase
) : ViewModel() {

    private val _listMovie = MutableLiveData<List<MovieList>>()
    val listMovie: LiveData<List<MovieList>> = _listMovie

    fun getAllMovieList() {
        viewModelScope.launch {
            val lists = getAllMovieInfoUseCase()
            _listMovie.value = lists
        }
    }
}