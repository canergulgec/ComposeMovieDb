package com.caner.data.repository.impl

import com.caner.common.utils.HttpRoutes
import com.caner.data.repository.MovieRepository
import com.caner.model.remote.MoviesResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val client: HttpClient) : MovieRepository {

    override suspend fun getPopularMovies(): MoviesResponse =
        client.get {
            url(HttpRoutes.POPULAR_MOVIES)
        }
}
