package com.example.dreamcinema.presentation.genreFragment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.useCases.GetMoviesByGenreUseCase
import javax.inject.Inject

class MovieByGenresSource @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val genreId: Int
) : PagingSource<Int, MovieDetailInfo>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDetailInfo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailInfo> {
        val page = params.key ?: 1
        val response = getMoviesByGenreUseCase(page, genreId)
        val totalPages = 5
        val prevKey = if (page == 1) null else page - 1
        val nextKey = if (page < totalPages) page + 1 else null

        return LoadResult.Page(response, prevKey, nextKey)
    }
}