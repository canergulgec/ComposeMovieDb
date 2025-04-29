package com.caner.home.state

import com.caner.domain.model.Movie

data class HomeUiState(
    val isFetchingMovies: Boolean = false,
    val popularMovies: List<Movie> = emptyList()
)
