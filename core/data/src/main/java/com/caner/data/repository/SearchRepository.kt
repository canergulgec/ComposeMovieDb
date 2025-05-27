package com.caner.data.repository

import com.caner.model.remote.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchMovie(query: String?): Flow<MoviesResponse>
}
