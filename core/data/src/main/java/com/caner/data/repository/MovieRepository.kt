package com.caner.data.repository

import com.caner.model.remote.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<MovieListResponse>
}
