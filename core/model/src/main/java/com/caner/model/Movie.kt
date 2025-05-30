package com.caner.model

data class Movie(
    val movieId: Int,
    val popularity: Double?,
    val poster: MovieImage?,
    val backdrop: MovieImage?,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: String?
)