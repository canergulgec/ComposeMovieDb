package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.repository.MovieDetailRepository
import com.caner.model.remote.MovieDetailResponse
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val api: MovieApi
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return api.getMovieDetail(movieId)
    }
}
