package com.caner.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.common.utils.HttpRoutes
import com.caner.data.model.remote.MoviesResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val client: HttpClient
) : MovieRepository {

    override fun getNowPlayingMovies() =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(client) }
        ).flow

    override suspend fun getPopularMovies(): MoviesResponse =
        client.get {
            url(HttpRoutes.POPULAR_MOVIES)
        }
}
