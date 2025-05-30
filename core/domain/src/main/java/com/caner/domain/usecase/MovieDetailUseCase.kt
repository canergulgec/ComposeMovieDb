package com.caner.domain.usecase

import com.caner.common.extension.catchNetworkError
import com.caner.common.extension.withLoading
import com.caner.common.network.Resource
import com.caner.data.repository.MovieDetailRepository
import com.caner.domain.di.IODispatcher
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.model.MovieDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(movieId: Int?): Flow<Resource<MovieDetailModel>> {
        return repository.getMovieDetail(movieId)
            .map { Resource.Success(mapper.transform(it)) }
            .withLoading()
            .catchNetworkError()
            .flowOn(ioDispatcher)
    }
}