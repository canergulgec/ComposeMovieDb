package com.caner.composemoviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.composemoviedb.utils.network.ApiError
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.data.model.remote.MoviesResponse
import com.caner.composemoviedb.domain.pagingsource.MoviesPagingSource
import com.caner.composemoviedb.utils.network.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val client: HttpClient,
    private val mapper: MovieMapper
) : MovieRepository {

    override fun getNowPlayingMovies() =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(client, mapper) }
        ).flow

    override suspend fun getPopularMovies(): Resource<MoviesResponse> {
        return try {
            val data: MoviesResponse = client.get {
                url(HttpRoutes.POPULAR_MOVIES)
            }
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(ApiError(message = e.message))
        }
    }
}
