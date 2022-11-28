package com.example.dreamcinema.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.useCases.GetMovieDetailInfoUseCase
import com.example.dreamcinema.domain.GetTopMovieInfoListUseCase
import com.example.dreamcinema.domain.MovieInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
    private val getTopMovieInfoListUseCase: GetTopMovieInfoListUseCase,
    private val getMovieDetailInfoUseCase: GetMovieDetailInfoUseCase
) : ViewModel() {

    private val _listMovie = MutableLiveData<List<MovieInfo>>()
    val listMovie: LiveData<List<MovieInfo>> = _listMovie

    fun getTopMovieList() {
        viewModelScope.launch {
            val movie = getTopMovieInfoListUseCase()
            _listMovie.value = movie
        }
    }
}