package com.caner.domain.model

data class Movie(
    val movieId: Int,
    val popularity: Double?,
    val poster: MovieImage?,
    val backdrop: MovieImage?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val releaseDate: String?
)