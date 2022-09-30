package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieDetailMapper
import com.caner.composemoviedb.domain.repository.MovieDetailRepository
import com.caner.composemoviedb.utils.extension.buildNetworkRequest
import com.caner.composemoviedb.utils.extension.mapTo
import com.caner.composemoviedb.utils.extension.onProgress
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