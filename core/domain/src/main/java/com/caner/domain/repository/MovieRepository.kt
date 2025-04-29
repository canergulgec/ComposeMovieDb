package com.caner.domain.repository

import com.caner.domain.model.remote.MoviesResponse

interface MovieRepository {

    suspend fun getPopularMovies(): MoviesResponse
}
