package com.caner.search.state

import com.caner.domain.model.Movie

sealed interface SearchUiState {
    val searchTitle: String
    val searchHint: String
    val isHintVisible: Boolean

    data class NoMovies(
        override val searchTitle: String,
        override val searchHint: String,
        override val isHintVisible: Boolean
    ) : SearchUiState

    data class HasMovies(
        val movies: List<Movie>,
        val isFetchingMovies: Boolean,
        override val searchTitle: String,
        override val searchHint: String,
        override val isHintVisible: Boolean
    ) : SearchUiState
}
