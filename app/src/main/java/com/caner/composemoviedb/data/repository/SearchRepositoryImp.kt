package com.caner.composemoviedb.data.repository

import com.caner.composemoviedb.data.model.remote.MoviesResponse
import com.caner.composemoviedb.utils.network.HttpParams
import com.caner.composemoviedb.utils.network.HttpRoutes
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