package com.caner.composemoviedb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.caner.composemoviedb.domain.model.Movie
import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.domain.repository.MovieRepository
import com.caner.composemoviedb.utils.extension.buildNetworkRequest
import com.caner.composemoviedb.utils.extension.mapTo
import com.caner.composemoviedb.utils.extension.onProgress
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieUseCase @Inject constructor(
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