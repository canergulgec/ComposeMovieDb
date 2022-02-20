package com.caner.composemoviedb.data.repository

import com.caner.composemoviedb.data.model.remote.MoviesResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): MoviesResponse
}
