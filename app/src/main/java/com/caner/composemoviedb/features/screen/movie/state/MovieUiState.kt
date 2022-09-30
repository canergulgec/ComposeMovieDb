package com.caner.composemoviedb.features.screen.movie.state

import androidx.paging.PagingData
import com.caner.composemoviedb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class MovieUiState(
    val isFetchingMovies: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: Flow<PagingData<Movie>>? = null,
)
