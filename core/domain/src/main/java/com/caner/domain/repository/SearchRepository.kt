package com.caner.domain.repository

import com.caner.domain.model.remote.MoviesResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): MoviesResponse
}
