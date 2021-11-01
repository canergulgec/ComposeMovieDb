package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.domain.viewstate.Resource
import com.caner.composemoviedb.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovie(query: String?): Flow<Resource<MovieModel>>
}
