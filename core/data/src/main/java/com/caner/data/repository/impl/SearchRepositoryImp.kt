package com.caner.data.repository.impl

import com.caner.common.utils.HttpParams
import com.caner.common.utils.HttpRoutes
import com.caner.data.repository.SearchRepository
import com.caner.model.remote.MoviesResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val client: HttpClient
) : SearchRepository {

    override fun searchMovie(query: String?): Flow<MoviesResponse> = flow {
        val response = client.get<MoviesResponse> {
            url(HttpRoutes.SEARCH_MOVIE)
            parameter(HttpParams.QUERY, query)
        }
        emit(response)
    }
}