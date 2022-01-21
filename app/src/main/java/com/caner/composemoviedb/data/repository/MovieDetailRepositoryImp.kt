package com.caner.composemoviedb.data.repository

import com.caner.composemoviedb.data.model.remote.MovieDetailResponse
import com.caner.composemoviedb.utils.network.ApiError
import com.caner.composemoviedb.utils.network.HttpRoutes
import com.caner.composemoviedb.utils.network.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val client: HttpClient
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?): Resource<MovieDetailResponse> {
        return try {
            val data: MovieDetailResponse = client.get {
                url(HttpRoutes.MOVIE_DETAIL.replace("{movie_id}", movieId.toString()))
            }
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(ApiError(message = e.message))
        }
    }
}
