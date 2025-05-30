package com.caner.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "popularity")
    val popularity: Double?,
    @Json(name = "video")
    val video: Boolean?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "adult")
    val adult: Boolean?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String?
)
