package com.caner.domain.usecase

import com.caner.common.extension.catchNetworkError
import com.caner.common.extension.withLoading
import com.caner.common.network.Resource
import com.caner.domain.repository.MovieDetailRepository
import com.caner.domain.di.IODispatcher
import com.caner.domain.model.MovieDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(movieId: Int): Flow<Resource<MovieDetailModel>> {
        return flow {
            val response = repository.getMovieDetail(movieId)
            emit(Resource.Success(response))
        }
            .withLoading()
            .catchNetworkError()
            .flowOn(ioDispatcher)
    }
}