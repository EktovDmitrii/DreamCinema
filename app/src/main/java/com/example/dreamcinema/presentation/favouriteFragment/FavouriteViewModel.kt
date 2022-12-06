package com.example.dreamcinema.presentation.favouriteFragment

import androidx.lifecycle.*
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.useCases.DeleteMovieUseCase
import com.example.dreamcinema.domain.useCases.GetMovieListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    val filteredLd = MutableLiveData<String>()

    private val _movieLD = MutableLiveData<List<MovieDetailInfo>>()
    val movieLD: LiveData<List<MovieDetailInfo>>
        get() = _movieLD

    private val _mainMovieLD = MediatorLiveData<List<MovieDetailInfo>>().apply {
        addSource(_movieLD) { value = onFilterChange() }
        addSource(filteredLd) { value = onFilterChange() }
    }
    val mainMovieLD: MediatorLiveData<List<MovieDetailInfo>>
        get() = _mainMovieLD

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

    fun onFilterChange(): List<MovieDetailInfo>? {
        val filterText = filteredLd.value ?: ""
        return _movieLD.value?.filter { it.title.contains(filterText, true) }
    }

    fun deleteFromFavourite(movieDetailInfo: MovieDetailInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovieUseCase(movieDetailInfo)
            getListFavouriteMovies()
        }
    }

}