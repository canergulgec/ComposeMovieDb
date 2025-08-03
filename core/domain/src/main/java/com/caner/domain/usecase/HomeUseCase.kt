package com.caner.domain.usecase

import com.caner.common.extension.catchNetworkError
import com.caner.common.extension.withLoading
import com.caner.common.network.Resource
import com.caner.domain.repository.MovieRepository
import com.caner.domain.di.IODispatcher
import com.caner.model.MovieList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Resource<MovieList>> {
        return flow {
            val response = repository.getPopularMovies()
            emit(Resource.Success(response))
        }
            .withLoading()
            .catchNetworkError()
            .flowOn(ioDispatcher)
    }
}
