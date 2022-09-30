package com.caner.domain.repository

import androidx.paging.PagingData
import com.caner.domain.model.remote.MovieResponseItem
import com.caner.domain.model.remote.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovies(): Flow<PagingData<MovieResponseItem>>
    suspend fun getPopularMovies(): MoviesResponse
}
