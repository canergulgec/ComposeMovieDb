package com.caner.home.state

import androidx.paging.PagingData
import com.caner.data.model.Movie
import kotlinx.coroutines.flow.Flow

data class HomeUiState(
    val isFetchingMovies: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: Flow<PagingData<Movie>>? = null,
)
