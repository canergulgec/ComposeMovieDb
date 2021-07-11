package com.caner.composemoviedb.domain.repository

import androidx.paging.PagingData
import com.caner.composemoviedb.data.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
}
