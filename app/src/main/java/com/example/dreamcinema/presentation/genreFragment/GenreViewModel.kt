package com.example.dreamcinema.presentation.genreFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dreamcinema.domain.Genre
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.useCases.GetGenreUseCase
import com.example.dreamcinema.domain.useCases.GetMoviesByGenreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val getGenreUseCase: GetGenreUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {

    private val _genre = MutableLiveData<List<Genre>>()
    val genre: LiveData<List<Genre>>
        get() = _genre

    private val _movie = MutableLiveData<List<MovieDetailInfo>>()
    val movie: LiveData<List<MovieDetailInfo>>
        get() = _movie

    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_PAGE_SIZE = 30
        private const val PREFETCH_DISTANCE = 5
        private const val INITIAL_LOAD_SIZE = 40
    }

    fun getMoviesByGenre(genreId: Int): Flow<PagingData<MovieDetailInfo>> {
        val config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true,
            maxSize = MAX_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE
        )
        return Pager(
            config,
            pagingSourceFactory = {
                MovieByGenresSource(
                    getMoviesByGenreUseCase,
                    genreId
                )
            }).flow.cachedIn(viewModelScope)
    }

    fun getGenreInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieGenre = getGenreUseCase()
            withContext(Dispatchers.Main) {
                _genre.value = movieGenre
            }
        }
    }
}