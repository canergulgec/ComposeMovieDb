package com.caner.data.repository

import com.caner.model.remote.MoviesResponse

interface MovieRepository {

    suspend fun getPopularMovies(): MoviesResponse
}
