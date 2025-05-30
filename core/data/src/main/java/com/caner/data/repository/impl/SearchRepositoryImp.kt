package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.repository.SearchRepository
import com.caner.model.remote.MovieListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val api: MovieApi
) : SearchRepository {

    override fun searchMovie(query: String): Flow<MovieListResponse> = flow {
        emit(api.searchMovie(query))
    }
}