package com.caner.data.repository

import com.caner.common.utils.HttpParams
import com.caner.common.utils.HttpRoutes
import com.caner.domain.model.remote.MoviesResponse
import com.caner.domain.repository.SearchRepository
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val client: HttpClient
) : SearchRepository {

    override suspend fun searchMovie(query: String?): MoviesResponse =
        client.get {
            url(HttpRoutes.SEARCH_MOVIE)
            parameter(HttpParams.QUERY, query)
        }
}