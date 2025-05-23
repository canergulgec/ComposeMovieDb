package com.caner.domain.usecase

import com.caner.common.extension.buildNetworkRequest
import com.caner.common.extension.mapTo
import com.caner.common.extension.onProgress
import com.caner.domain.mapper.MovieMapper
import com.caner.data.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper
) {

    fun searchMovie(query: String) = flow {
        val response = repository.searchMovie(query)
        response.apply {
            val sortedList = results.sortedByDescending { it.popularity }
            results = sortedList
            emit(this.mapTo(mapper))
        }
    }
        .onProgress()
        .buildNetworkRequest()
}