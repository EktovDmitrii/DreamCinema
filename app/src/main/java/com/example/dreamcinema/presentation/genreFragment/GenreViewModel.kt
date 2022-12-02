package com.example.dreamcinema.presentation.genreFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcinema.domain.Genre
import com.example.dreamcinema.domain.useCases.GetGenreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val getGenreUseCase: GetGenreUseCase
): ViewModel() {

    private val _genre = MutableLiveData<List<Genre>>()
    val genre: LiveData<List<Genre>>
    get() = _genre

    fun getGenreInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            val movieGenre = getGenreUseCase()
            withContext(Dispatchers.Main){
                _genre.value = movieGenre
            }
        }
    }
}