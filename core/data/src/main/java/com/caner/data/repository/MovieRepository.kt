package com.caner.data.repository

import com.caner.model.remote.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<MoviesResponse>
}
