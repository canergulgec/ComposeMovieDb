package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.repository.MovieRepository
import com.caner.model.remote.MovieListResponse
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): MovieListResponse {
        return api.getPopularMovies()
    }
}
