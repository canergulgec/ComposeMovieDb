package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieDetailMapper
import com.caner.composemoviedb.data.repository.MovieDetailRepository
import com.caner.composemoviedb.utils.extension.mapTo
import com.caner.composemoviedb.utils.extension.onProgress
import com.caner.composemoviedb.utils.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) {

    fun getMovieDetail(movieId: Int?) = flow {
        val response = repository.getMovieDetail(movieId)
        emit(response.mapTo(mapper))
    }
        .catch { error ->
            emit(Resource.Error(Throwable(message = error.message)))
            emit(Resource.Loading(false))
        }
        .onProgress()
        .flowOn(Dispatchers.IO)
}