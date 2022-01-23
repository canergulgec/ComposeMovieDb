package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieDetailMapper
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.utils.extension.toModel
import com.caner.composemoviedb.utils.qualifier.IoDispatcher
import com.caner.composemoviedb.data.repository.MovieDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildRequest(params: Int?) = flow {
        when (val response = repository.getMovieDetail(params)) {
            is Resource.Success -> emit(response.data.toModel(mapper))
            is Resource.Loading -> emit(Resource.Loading)
            is Resource.Error -> emit(Resource.Error(response.apiError))
        }
    }
        .flowOn(dispatcher)
}