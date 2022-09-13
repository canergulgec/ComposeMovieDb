package com.caner.data.repository

import androidx.paging.PagingData
import com.caner.data.model.remote.MovieResponseItem
import com.caner.data.model.remote.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovies(): Flow<PagingData<MovieResponseItem>>
    suspend fun getPopularMovies(): MoviesResponse
}
