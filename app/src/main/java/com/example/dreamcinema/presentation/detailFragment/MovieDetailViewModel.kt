package com.example.dreamcinema.presentation.detailFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.MovieCast
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.MovieVideos
import com.example.dreamcinema.domain.useCases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getDetailInfoUseCase: GetDetailInfoUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase,
    private val addMovieUseCase: AddMovieUseCase,
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetailInfo>()
    val movie: LiveData<MovieDetailInfo>
        get() = _movie

    private val _video = MutableLiveData<List<MovieVideos>>()
    val video: LiveData<List<MovieVideos>>
        get() = _video

    private val _favouriteMovie = MutableLiveData<MovieDetailInfo>()
    val favouriteMovie: LiveData<MovieDetailInfo>
        get() = _favouriteMovie

    private val _cast = MutableLiveData<List<MovieCast>>()
    val cast: LiveData<List<MovieCast>>
        get() = _cast

    private val _recommendation = MutableLiveData<List<MovieInfo>>()
    val recommendation: LiveData<List<MovieInfo>>
        get() = _recommendation


    fun getDetailsInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetail = getDetailInfoUseCase(id)
            withContext((Dispatchers.Main)) {
                _movie.value = movieDetail
            }
        }
    }

    fun getCastInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val castInfo = getMovieCastUseCase(id)
            withContext((Dispatchers.Main)) {
                _cast.value = castInfo
                Log.d("CastCheck", "${castInfo.toString()}")
            }
        }
    }

    fun getRecommendedMovies(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val recommendation = getRecommendedMoviesUseCase(id)
            withContext((Dispatchers.Main)) {
                _recommendation.value = recommendation
            }
        }
    }

    fun addFavouriteMovie(movieDetailInfo: MovieDetailInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            addMovieUseCase(movieDetailInfo)
        }
    }

    fun getVideo(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val trailers = getVideosUseCase(id)
            withContext(Dispatchers.Main){
                _video.value = trailers
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}