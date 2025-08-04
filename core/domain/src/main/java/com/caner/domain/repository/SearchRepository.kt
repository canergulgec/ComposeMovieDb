package com.caner.domain.repository

import com.caner.domain.model.MovieList

interface SearchRepository {

    suspend fun searchMovie(query: String): MovieList
}
