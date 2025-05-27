package com.caner.data.repository.impl

import com.caner.common.utils.HttpRoutes
import com.caner.data.repository.MovieDetailRepository
import com.caner.model.remote.MovieDetailResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val client: HttpClient
) : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int?): Flow<MovieDetailResponse> = flow {
        val response = client.get<MovieDetailResponse> {
            url(HttpRoutes.MOVIE_DETAIL.replace("{movie_id}", movieId.toString()))
        }
        emit(response)
    }
}
