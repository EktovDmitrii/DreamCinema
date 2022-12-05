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
    private val getMovieUseCase: GetMovieUseCase
): ViewModel() {

    private val _movieLD = MutableLiveData<MovieInfo>()
    val movieLD: LiveData<MovieInfo>
        get() = _movieLD

    fun getListFavouriteMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = getMovieUseCase(movieId)
            _movieLD.value = movieList
        }
    }

}