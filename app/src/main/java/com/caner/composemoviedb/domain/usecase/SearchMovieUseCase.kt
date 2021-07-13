package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.base.BaseUseCase
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.MovieModel
import com.caner.composemoviedb.domain.qualifier.IoDispatcher
import com.caner.composemoviedb.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class SearchMovieUseCase @Inject constructor(
    private val apiRepository: SearchRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieModel, String?>() {

    override fun buildRequest(params: String?): Flow<Resource<MovieModel>> {
        return apiRepository.searchMovie(params)
            .onStart { emit(Resource.Loading) }
            .flatMapConcat { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data.movies.isEmpty()) {
                            flow { emit(Resource.Empty) }
                        } else {
                            flow { emit(resource) }
                        }
                    }
                    else -> {
                        flow { emit(resource) }
                    }
                }
            }
            .flowOn(dispatcher)
    }
}