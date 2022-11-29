package com.example.dreamcinema.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.useCases.GetDetailInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getDetailInfoUseCase: GetDetailInfoUseCase
): ViewModel() {

    private val _movie = MutableLiveData<MovieInfo>()
    val movie: LiveData<MovieInfo> = _movie

    fun getDetailsInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetail = getDetailInfoUseCase(id)
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