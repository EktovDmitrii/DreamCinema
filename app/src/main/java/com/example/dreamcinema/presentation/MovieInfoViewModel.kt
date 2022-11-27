package com.example.dreamcinema.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.data.network.MovieInfoDto
import com.example.dreamcinema.data.network.api.ApiService
import com.example.dreamcinema.domain.GetMovieDetailInfoUseCase
import com.example.dreamcinema.domain.GetMovieInfoListUseCase
import com.example.dreamcinema.domain.MovieInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
//    private val getMovieInfoListUseCase: GetMovieInfoListUseCase,
    private val apiService: ApiService,
    private val getMovieDetailInfoUseCase: GetMovieDetailInfoUseCase
) : ViewModel() {

    private val _listMovie = MutableLiveData<List<MovieInfoDto>>()
    val listMovie: LiveData<List<MovieInfoDto>> = _listMovie

    fun getTopMovieList() {
        viewModelScope.launch {
            val movie = apiService.getTopMoviesInfo().movieList
            _listMovie.value = movie
        }
    }
}