package com.caner.domain.repository

import com.caner.model.MovieList

interface MovieRepository {

    suspend fun getPopularMovies(): MovieList
}
