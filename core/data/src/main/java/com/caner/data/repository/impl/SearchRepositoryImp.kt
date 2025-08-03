package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.domain.repository.SearchRepository
import com.caner.model.remote.MovieListResponse
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val api: MovieApi
) : SearchRepository {

    override suspend fun searchMovie(query: String): MovieListResponse {
        return api.searchMovie(query)
    }
}