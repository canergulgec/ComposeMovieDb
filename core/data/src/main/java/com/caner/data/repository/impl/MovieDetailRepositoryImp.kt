package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.repository.MovieDetailRepository
import com.caner.model.remote.MovieDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val api: MovieApi
) : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int): Flow<MovieDetailResponse> = flow {
        emit(api.getMovieDetail(movieId))
    }
}
