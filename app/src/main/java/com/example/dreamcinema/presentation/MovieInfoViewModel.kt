package com.example.dreamcinema.presentation

import androidx.lifecycle.ViewModel
import com.example.dreamcinema.domain.GetMovieDetailInfoUseCase
import com.example.dreamcinema.domain.GetMovieInfoListUseCase
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(
private val getMovieInfoListUseCase: GetMovieInfoListUseCase,
private val getMovieDetailInfoUseCase: GetMovieDetailInfoUseCase
): ViewModel() {

    val movieInfoList = getMovieInfoListUseCase()
}