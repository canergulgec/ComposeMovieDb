package com.caner.domain.usecase

import com.caner.common.extension.buildNetworkRequest
import com.caner.common.extension.mapTo
import com.caner.common.extension.onProgress
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) {

    fun getMovieDetail(movieId: Int?) = flow {
        val response = repository.getMovieDetail(movieId)
        emit(response.mapTo(mapper))
    }
        .onProgress()
        .buildNetworkRequest()
}