package com.caner.composemoviedb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.composemoviedb.common.ApiError
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.common.ext.filterMapperResponse
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.domain.paging.MoviesPagingSource
import com.caner.composemoviedb.domain.api.MovieApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
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
    }
}
