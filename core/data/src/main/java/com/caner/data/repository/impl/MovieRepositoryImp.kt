package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.repository.MovieRepository
import com.caner.model.remote.MovieListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun getPopularMovies(): Flow<MovieListResponse> = flow {
        emit(api.getPopularMovies())
    }
}
