package com.caner.domain.usecase

import com.caner.common.extension.buildNetworkRequest
import com.caner.common.extension.mapTo
import com.caner.common.extension.onProgress
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.repository.MovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: MovieMapper
) {
    fun getPopularMovies() = flow {
        val response = movieRepository.getPopularMovies()
        emit(response.mapTo(mapper))
    }
        .onProgress()
        .buildNetworkRequest()
}