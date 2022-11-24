package com.example.dreamcinema.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke() = repository.loadData()
}