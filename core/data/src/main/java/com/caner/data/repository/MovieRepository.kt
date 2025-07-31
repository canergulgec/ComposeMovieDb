package com.caner.data.repository

import com.caner.model.remote.MovieListResponse

interface MovieRepository {

    suspend fun getPopularMovies(): MovieListResponse
}
