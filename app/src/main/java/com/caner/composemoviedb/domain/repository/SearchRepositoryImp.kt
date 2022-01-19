package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.data.model.remote.MoviesResponse
import com.caner.composemoviedb.data.viewstate.ApiError
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.domain.HttpParams
import com.caner.composemoviedb.domain.HttpRoutes
import com.caner.composemoviedb.domain.extension.filterMapperResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val client: HttpClient,
    private val mapper: MovieMapper
) : SearchRepository {

     override fun searchMovie(query: String?) = flow {
         try {
             val data: MoviesResponse = client.get {
                 url(HttpRoutes.SEARCH_MOVIE)
                 parameter(HttpParams.QUERY, query)
             }
             emit(data.filterMapperResponse(mapper))
         } catch (e: Exception) {
             emit(Resource.Error(ApiError(message = e.message)))
         }
    }
}