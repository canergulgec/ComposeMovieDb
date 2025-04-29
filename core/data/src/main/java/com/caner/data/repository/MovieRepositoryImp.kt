package com.caner.data.repository

import com.caner.common.utils.HttpRoutes
import com.caner.domain.model.remote.MoviesResponse
import com.caner.domain.repository.MovieRepository
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val client: HttpClient) : MovieRepository {

    override suspend fun getPopularMovies(): MoviesResponse =
        client.get {
            url(HttpRoutes.POPULAR_MOVIES)
        }
}
