package com.caner.home.state

import com.caner.model.Movie

data class HomeUiState(
    val isLoading: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val error: String? = null
)
