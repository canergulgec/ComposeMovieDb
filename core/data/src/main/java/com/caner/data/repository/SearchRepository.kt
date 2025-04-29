package com.caner.data.repository

import com.caner.model.remote.MoviesResponse

interface SearchRepository {

    suspend fun searchMovie(query: String?): MoviesResponse
}
