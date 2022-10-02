package com.caner.domain.repository

import com.caner.domain.model.remote.MoviesResponse

interface MovieRepository {

    suspend fun getNowPlayingMovies(page: Int): MoviesResponse

    suspend fun getPopularMovies(): MoviesResponse
}
