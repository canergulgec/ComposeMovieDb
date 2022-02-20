package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.data.model.MovieModel
import com.caner.composemoviedb.data.repository.SearchRepository
import com.caner.composemoviedb.utils.extension.mapTo
import com.caner.composemoviedb.utils.extension.onProgress
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper
) : BaseUseCase<MovieModel, String?>() {

    override fun buildResponse(params: String?) = flow {
        val response = repository.searchMovie(params)
        response.apply {
            val sortedList = results.sortedByDescending { it.popularity }
            results = sortedList
            emit(this.mapTo(mapper))
        }
    }
        .onProgress()
}