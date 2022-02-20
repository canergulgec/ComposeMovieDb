package com.caner.composemoviedb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.data.model.MovieModel
import com.caner.composemoviedb.data.repository.MovieRepository
import com.caner.composemoviedb.utils.extension.mapTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) : BaseUseCase<MovieModel, Int?>() {

    override fun buildResponse(params: Int?) = flow {
        val response = repository.getPopularMovies()
        emit(response.mapTo(mapper))
    }

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return repository.getNowPlayingMovies()
            .map { pagingData ->
                pagingData.map {
                    mapper.toMovie(it)
                }
            }
    }
}