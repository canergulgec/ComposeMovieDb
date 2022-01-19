package com.caner.composemoviedb.data.model

data class MovieModel(
    val total: Int = 0,
    val page: Int = 0,
    var movies: List<Movie>
)
