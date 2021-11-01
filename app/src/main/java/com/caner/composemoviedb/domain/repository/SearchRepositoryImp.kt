package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.domain.extension.filterMapperResponse
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.domain.api.SearchApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val apiService: SearchApi,
    private val movieMapper: MovieMapper
) : SearchRepository {

    override fun searchMovie(query: String?) = flow {
        val data = apiService.searchMovie(query)
        emit(data.filterMapperResponse(movieMapper))
    }
}