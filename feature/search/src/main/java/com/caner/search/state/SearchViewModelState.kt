package com.caner.search.state

import com.caner.model.Movie

data class SearchUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val searchTitle: String = "",
    val isHintVisible: Boolean = false,
)