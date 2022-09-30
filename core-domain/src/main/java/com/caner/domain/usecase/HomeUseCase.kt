package com.caner.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.caner.common.extension.buildNetworkRequest
import com.caner.common.extension.mapTo
import com.caner.common.extension.onProgress
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.model.Movie
import com.caner.domain.repository.MovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) {

    fun getPopularMovies() = flow {
        val response = repository.getPopularMovies()
        emit(response.mapTo(mapper))
    }
        .onProgress()
        .buildNetworkRequest()

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return repository.getNowPlayingMovies()
            .map { pagingData ->
                pagingData.map {
                    mapper.toMovie(it)
                }
            }
    }
}