package com.caner.search.state

import com.caner.data.model.Movie

data class SearchViewModelState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val title: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = false,
) {

    fun toUiState(): SearchUiState =
        if (movies.isNotEmpty() || isLoading) {
            SearchUiState.HasMovies(
                movies = movies,
                isFetchingMovies = isLoading,
                searchTitle = title,
                searchHint = hint,
                isHintVisible = isHintVisible
            )
        } else {
            SearchUiState.NoMovies(
                searchTitle = title,
                searchHint = hint,
                isHintVisible = isHintVisible
            )
        }
}