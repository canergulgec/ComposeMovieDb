package com.caner.data.repository

import com.caner.model.remote.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchMovie(query: String): Flow<MovieListResponse>
}
