package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.data.mapper.MovieDetailMapper
import com.caner.composemoviedb.data.model.remote.MovieDetailResponse
import com.caner.composemoviedb.data.viewstate.ApiError
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.domain.HttpRoutes
import com.caner.composemoviedb.domain.extension.filterMapperResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val client: HttpClient,
    private val mapper: MovieDetailMapper,
) : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int?) = flow {
        try {
            val data: MovieDetailResponse = client.get {
                url(HttpRoutes.MOVIE_DETAIL.replace("{movie_id}", movieId.toString()))
            }
            emit(data.filterMapperResponse(mapper))
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(message = e.message)))
        }
    }
}
