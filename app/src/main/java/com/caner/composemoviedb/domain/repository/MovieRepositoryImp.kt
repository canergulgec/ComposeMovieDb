package com.caner.composemoviedb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.composemoviedb.data.viewstate.ApiError
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.data.model.remote.MoviesResponse
import com.caner.composemoviedb.domain.HttpRoutes
import com.caner.composemoviedb.domain.extension.filterMapperResponse
import com.caner.composemoviedb.domain.pagingsource.MoviesPagingSource
import com.caner.composemoviedb.domain.qualifier.IoDispatcher
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val client: HttpClient,
    private val mapper: MovieMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override fun getNowPlayingMovies() =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(client, mapper) }
        ).flow

    override fun getPopularMovies() = flow {
        try {
            val data: MoviesResponse = client.get {
                url(HttpRoutes.POPULAR_MOVIES)
            }
            emit(data.filterMapperResponse(mapper))
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(message = e.message)))
        }
    }.flowOn(dispatcher)
}
