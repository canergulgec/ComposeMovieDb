package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.domain.model.remote.MoviesResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): MoviesResponse
}
