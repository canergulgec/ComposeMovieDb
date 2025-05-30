package com.caner.data.repository.impl

import com.caner.common.utils.HttpRoutes
import com.caner.data.repository.MovieRepository
import com.caner.model.remote.MovieListResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val client: HttpClient) : MovieRepository {

    override fun getPopularMovies(): Flow<MovieListResponse> = flow {
        val response = client.get<MovieListResponse> {
            url(HttpRoutes.POPULAR_MOVIES)
        }
        emit(response)
    }
}
