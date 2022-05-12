package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.data.repository.SearchRepository
import com.caner.composemoviedb.utils.extension.buildNetworkRequest
import com.caner.composemoviedb.utils.extension.mapTo
import com.caner.composemoviedb.utils.extension.onProgress
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