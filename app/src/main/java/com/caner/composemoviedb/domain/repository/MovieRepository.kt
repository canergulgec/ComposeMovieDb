package com.caner.composemoviedb.domain.repository

import androidx.paging.PagingData
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.Movie
import com.caner.composemoviedb.data.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(type: Int): Flow<PagingData<Movie>>
    fun getPopularMovies(): Flow<Resource<MovieModel>>
}
