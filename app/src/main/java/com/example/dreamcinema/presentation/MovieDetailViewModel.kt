package com.example.dreamcinema.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.data.network.MovieCastDto
import com.example.dreamcinema.data.network.api.ApiService
import com.example.dreamcinema.domain.MovieCast
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.useCases.GetDetailInfoUseCase
import com.example.dreamcinema.domain.useCases.GetMovieCastUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getDetailInfoUseCase: GetDetailInfoUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieInfo>()
    val movie: LiveData<MovieInfo>
        get() = _movie

    private val _cast = MutableLiveData<List<MovieCast>>()
    val cast: LiveData<List<MovieCast>>
        get() = _cast


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

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}