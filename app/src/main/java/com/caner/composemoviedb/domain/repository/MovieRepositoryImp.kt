package com.caner.composemoviedb.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.domain.paging.MoviesPagingSource
import com.caner.composemoviedb.domain.api.MovieApi
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getMovies() =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(movieApi, movieMapper) }
        ).flow
}
