package com.caner.composemoviedb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.data.model.MovieModel
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.utils.extension.toModel
import com.caner.composemoviedb.utils.qualifier.IoDispatcher
import com.caner.composemoviedb.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieModel, Int?>() {

    override fun buildRequest(params: Int?) = flow {
        when (val response = repository.getPopularMovies()) {
            is Resource.Success -> emit(response.data.toModel(mapper))
            is Resource.Loading -> emit(Resource.Loading)
            is Resource.Error -> emit(Resource.Error(response.apiError))
        }
    }
        .flowOn(dispatcher)

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return repository.getNowPlayingMovies()
            .map { pagingData ->
                pagingData.map {
                    mapper.toMovie(it)
                }
            }
    }
}