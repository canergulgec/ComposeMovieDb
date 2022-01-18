package com.caner.composemoviedb.domain.repository

import androidx.paging.PagingData
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    fun getPopularMovies(): Flow<Resource<MovieModel>>
}
