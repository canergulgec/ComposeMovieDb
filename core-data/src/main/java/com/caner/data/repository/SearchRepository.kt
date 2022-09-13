package com.caner.data.repository

import com.caner.data.model.remote.MoviesResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): MoviesResponse
}
