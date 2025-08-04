package com.caner.domain.repository

import com.caner.domain.model.MovieList

interface MovieRepository {

    suspend fun getPopularMovies(): MovieList
}
