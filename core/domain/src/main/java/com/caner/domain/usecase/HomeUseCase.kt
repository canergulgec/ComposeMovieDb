package com.caner.domain.usecase

import com.caner.common.extension.catchNetworkError
import com.caner.common.extension.withLoading
import com.caner.common.network.Resource
import com.caner.domain.mapper.MovieMapper
import com.caner.data.repository.MovieRepository
import com.caner.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) {

    operator fun invoke(): Flow<Resource<MovieModel>> {
        return repository.getPopularMovies()
            .map { Resource.Success(mapper.transform(it)) }
            .withLoading()
            .catchNetworkError()
            .flowOn(Dispatchers.IO)
    }
}
