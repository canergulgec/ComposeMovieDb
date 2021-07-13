package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.MovieModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovie(query: String?): Flow<Resource<MovieModel>>
}
