package com.caner.model

data class MovieList(
    val total: Int = 0,
    val page: Int = 0,
    val movies: List<Movie>
)
