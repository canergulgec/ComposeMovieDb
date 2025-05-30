package com.caner.data.repository

import com.caner.model.remote.MovieDetailResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    fun getMovieDetail(movieId: Int): Flow<MovieDetailResponse>
}