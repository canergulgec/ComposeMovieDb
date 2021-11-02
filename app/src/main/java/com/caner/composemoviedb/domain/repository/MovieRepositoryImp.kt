package com.caner.composemoviedb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.composemoviedb.data.viewstate.ApiError
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.domain.extension.filterMapperResponse
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.domain.pagingsource.MoviesPagingSource
import com.caner.composemoviedb.domain.api.MovieApi
import com.caner.composemoviedb.domain.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override fun getMovies(type: Int) =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(movieApi, movieMapper, type) }
        ).flow

    override fun getPopularMovies() = flow {
        val data = movieApi.getPopularMovies()
        emit(data.filterMapperResponse(movieMapper))
    }.catch { error ->
        emit(Resource.Error(ApiError(4, error.message)))
    }.flowOn(dispatcher)
}
