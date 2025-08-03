package com.caner.domain.usecase

import com.caner.common.extension.catchNetworkError
import com.caner.common.extension.withLoading
import com.caner.common.network.Resource
import com.caner.domain.repository.SearchRepository
import com.caner.domain.di.IODispatcher
import com.caner.model.MovieList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(query: String): Flow<Resource<MovieList>> {
        return flow {
            emit(repository.searchMovie(query))
        }
            .map { movieList ->
                val sortedMovies = movieList.movies.sortedByDescending { it.popularity }
                Resource.Success(movieList.copy(movies = sortedMovies))
            }
            .withLoading()
            .catchNetworkError()
            .flowOn(ioDispatcher)
    }
}