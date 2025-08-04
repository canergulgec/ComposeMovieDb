package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.mapper.MovieMapper
import com.caner.domain.repository.MovieRepository
import com.caner.domain.model.MovieList
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val api: MovieApi,
    private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun getPopularMovies(): MovieList {
        val response = api.getPopularMovies()
        return mapper.transform(response)
    }
}
