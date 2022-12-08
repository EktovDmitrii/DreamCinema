package com.example.dreamcinema.presentation.favouriteFragment

import androidx.lifecycle.*
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.useCases.DeleteMovieUseCase
import com.example.dreamcinema.domain.useCases.GetMovieListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _filteredLD = MutableLiveData<String>()
    val filteredLD: MutableLiveData<String>
        get() = _filteredLD

    private val movieLD = MutableLiveData<List<MovieDetailInfo>>()

    private val _mainMovieLD = MediatorLiveData<List<MovieDetailInfo>>().apply {
        addSource(movieLD) { value = onFilterChange() }
        addSource(_filteredLD) { value = onFilterChange() }
    }
    val mainMovieLD: MediatorLiveData<List<MovieDetailInfo>>
        get() = _mainMovieLD

    fun getListFavouriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = getMovieListUseCase()
            withContext(Dispatchers.Main) {
                if (movieList != null) {
                    movieLD.value = movieList
                } else {
                    throw RuntimeException("ListOfFavourite equals null")
                }
            }
        }
    }

    fun onFilterChange(): List<MovieDetailInfo>? {
        val filterText = _filteredLD.value ?: ""
        return movieLD.value?.filter { it.title.contains(filterText, true) }
    }

    fun deleteFromFavourite(movieDetailInfo: MovieDetailInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovieUseCase(movieDetailInfo)
            getListFavouriteMovies()
        }
    }

}