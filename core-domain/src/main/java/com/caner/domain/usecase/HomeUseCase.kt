package com.caner.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.caner.common.extension.buildNetworkRequest
import com.caner.common.extension.mapTo
import com.caner.common.extension.onProgress
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.model.Movie
import com.caner.domain.pagingsource.MoviesPagingSource
import com.caner.domain.repository.MovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: MovieMapper
) {

    fun getPopularMovies() = flow {
        val response = movieRepository.getPopularMovies()
        emit(response.mapTo(mapper))
    }
        .onProgress()
        .buildNetworkRequest()

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        val moviePager = Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(repository = movieRepository) }
        ).flow

        return moviePager.map { pagingData ->
            pagingData.map {
                mapper.toMovie(it)
            }
        }
    }
}