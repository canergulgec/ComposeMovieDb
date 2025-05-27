package com.caner.domain.usecase

import com.caner.common.extension.catchNetworkError
import com.caner.common.extension.withLoading
import com.caner.common.network.Resource
import com.caner.domain.mapper.MovieMapper
import com.caner.data.repository.SearchRepository
import com.caner.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper
) {

    operator fun invoke(query: String): Flow<Resource<MovieModel>> {
        return repository.searchMovie(query)
            .map { response ->
                val sorted = response.copy(
                    results = response.results.sortedByDescending { it.popularity }
                )
                Resource.Success(mapper.transform(sorted))
            }
            .withLoading()
            .catchNetworkError()
            .flowOn(Dispatchers.IO)
    }
}