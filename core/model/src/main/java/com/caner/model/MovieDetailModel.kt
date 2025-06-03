package com.caner.model

import com.caner.model.remote.MovieGenre
import java.util.*

data class MovieDetailModel(
    val movieId: Int,
    val popularity: Double? = null,
    val video: Boolean? = null,
    val adult: Boolean? = null,
    val poster: MovieImage? = null,
    val backdrop: MovieImage? = null,
    val genres: List<MovieGenre> = listOf(),
    val title: String,
    val overview: String,
    val imdbId: String? = null,
    val runtime: Int? = null,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String?
)