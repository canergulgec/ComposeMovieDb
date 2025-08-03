package com.caner.domain.repository

import com.caner.model.remote.MovieListResponse

interface SearchRepository {

    suspend fun searchMovie(query: String): MovieListResponse
}
