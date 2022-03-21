package com.caner.composemoviedb.view.movie.state

import com.caner.composemoviedb.data.model.Movie

data class MovieUiState(
    val popularMovies: List<Movie> = emptyList(),
    val isFetchingMovies: Boolean = false
)
