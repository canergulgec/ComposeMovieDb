package com.caner.data.repository

import com.caner.common.utils.HttpParams
import com.caner.common.utils.HttpRoutes
import com.caner.domain.model.remote.MoviesResponse
import com.caner.domain.repository.MovieRepository
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val client: HttpClient
) : MovieRepository {

    override suspend fun getNowPlayingMovies(page: Int): MoviesResponse =
        client.get {
            url(HttpRoutes.NOW_PLAYING_MOVIES)
            parameter(HttpParams.PAGE, page)
        }

    override suspend fun getPopularMovies(): MoviesResponse =
        client.get {
            url(HttpRoutes.POPULAR_MOVIES)
        }
}
