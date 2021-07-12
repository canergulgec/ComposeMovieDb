package com.caner.composemoviedb.data

import com.caner.composemoviedb.data.remote.MovieGenre
import java.util.*

data class MovieDetailModel(
    val movieId: Int,
    val popularity: Double? = null,
    val video: Boolean? = null,
    val adult: Boolean? = null,
    val poster: MovieImage? = null,
    val backdrop: MovieImage? = null,
    val genres: List<MovieGenre>? = null,
    val title: String? = null,
    val overview: String? = null,
    val imdbId: String? = null,
    val runtime: Int? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val releaseDate: Date? = null
)