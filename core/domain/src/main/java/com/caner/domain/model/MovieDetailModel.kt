package com.caner.domain.model

data class MovieDetailModel(
    val movieId: Int,
    val popularity: Double? = null,
    val video: Boolean? = null,
    val adult: Boolean? = null,
    val poster: MovieImage? = null,
    val backdrop: MovieImage? = null,
    val genres: List<MovieGenre>? = null,
    val title: String,
    val overview: String,
    val imdbId: String? = null,
    val runtime: Int? = null,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String?
)