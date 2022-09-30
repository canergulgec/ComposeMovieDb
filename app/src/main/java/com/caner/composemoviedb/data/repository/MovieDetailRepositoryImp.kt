package com.caner.composemoviedb.data.repository

import com.caner.composemoviedb.domain.model.remote.MovieDetailResponse
import com.caner.composemoviedb.domain.repository.MovieDetailRepository
import com.caner.composemoviedb.utils.network.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val client: HttpClient
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?): MovieDetailResponse =
        client.get {
            url(HttpRoutes.MOVIE_DETAIL.replace("{movie_id}", movieId.toString()))
        }
}
