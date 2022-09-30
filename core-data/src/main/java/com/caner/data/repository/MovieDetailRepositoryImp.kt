package com.caner.data.repository

import com.caner.common.utils.HttpRoutes
import com.caner.domain.model.remote.MovieDetailResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val client: HttpClient
) : com.caner.domain.repository.MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?): MovieDetailResponse =
        client.get {
            url(HttpRoutes.MOVIE_DETAIL.replace("{movie_id}", movieId.toString()))
        }
}
