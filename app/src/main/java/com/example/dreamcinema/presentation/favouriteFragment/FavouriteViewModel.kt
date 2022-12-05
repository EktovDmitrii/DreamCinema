package com.example.dreamcinema.presentation.favouriteFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.domain.useCases.DeleteMovieUseCase
import com.example.dreamcinema.domain.useCases.GetMovieListUseCase
import com.example.dreamcinema.domain.useCases.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _movieLD = MutableLiveData<List<MovieInfo>>()
    val movieLD: LiveData<List<MovieInfo>>
        get() = _movieLD

    fun getListFavouriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = getMovieListUseCase()
            withContext(Dispatchers.Main) {
                let {
                    _movieLD.value = movieList
                }
            }
        }
    }

    fun deleteFromFavourite(movieInfo: MovieInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovieUseCase(movieInfo)
            getListFavouriteMovies()
        }
    }

}