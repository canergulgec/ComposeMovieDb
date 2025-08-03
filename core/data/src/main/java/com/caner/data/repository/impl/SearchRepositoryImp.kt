package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.mapper.MovieMapper
import com.caner.domain.repository.SearchRepository
import com.caner.model.MovieList
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val api: MovieApi,
    private val mapper: MovieMapper,
) : SearchRepository {

    override suspend fun searchMovie(query: String): MovieList {
        val response = api.searchMovie(query)
        return mapper.transform(response)
    }
}