package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.data.model.MovieModel
import com.caner.composemoviedb.utils.extension.toModel
import com.caner.composemoviedb.utils.qualifier.IoDispatcher
import com.caner.composemoviedb.data.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieModel, String?>() {

    override fun buildRequest(params: String?) = flow {
        when (val response = repository.searchMovie(params)) {
            is Resource.Success -> {
                response.data.apply {
                    val sortedList = results.sortedByDescending { it.popularity }
                    results = sortedList
                    emit(this.toModel(mapper))
                }
            }
            is Resource.Loading -> emit(Resource.Loading)
            is Resource.Error -> emit(Resource.Error(response.apiError))
        }
    }
        .onStart { emit(Resource.Loading) }
        .flowOn(dispatcher)
}