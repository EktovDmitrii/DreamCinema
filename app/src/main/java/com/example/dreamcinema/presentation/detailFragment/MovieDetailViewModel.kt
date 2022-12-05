package com.example.dreamcinema.presentation.detailFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.MovieCast
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.useCases.AddMovieUseCase
import com.example.dreamcinema.domain.useCases.GetDetailInfoUseCase
import com.example.dreamcinema.domain.useCases.GetMovieCastUseCase
import com.example.dreamcinema.domain.useCases.GetRecommendedMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getDetailInfoUseCase: GetDetailInfoUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase,
    private val addMovieUseCase: AddMovieUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieInfo>()
    val movie: LiveData<MovieInfo>
        get() = _movie

    private val _favouriteMovie = MutableLiveData<MovieInfo>()
    val favouriteMovie: LiveData<MovieInfo>
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

    fun addFavouriteMovie(movieInfo: MovieInfo){
        viewModelScope.launch(Dispatchers.IO) {
           addMovieUseCase(movieInfo)

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}