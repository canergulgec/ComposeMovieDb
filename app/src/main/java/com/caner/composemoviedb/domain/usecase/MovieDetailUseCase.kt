package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.domain.qualifier.IoDispatcher
import com.caner.composemoviedb.domain.repository.MovieDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val apiRepository: MovieDetailRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildRequest(params: Int?): Flow<Resource<MovieDetailModel>> {
        return apiRepository.getMovieDetail(params)
            .onStart { emit(Resource.Loading) }
            .flowOn(dispatcher)
    }
}