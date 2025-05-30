package com.caner.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "popularity")
    val popularity: Double? = null,
    @Json(name = "video")
    val video: Boolean? = false,
    @Json(name = "adult")
    val adult: Boolean? = false,
    @Json(name = "backdrop_path")
    val backdrop_path: String? = null,
    @Json(name = "poster_path")
    val poster_path: String? = null,
    @Json(name = "genres")
    val genres: List<MovieGenre>? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "overview")
    val overview: String? = null,
    @Json(name = "imdb_id")
    val imdb_id: String? = null,
    @Json(name = "runtime")
    val runtime: Int? = null,
    @Json(name = "vote_average")
    val vote_average: Double? = null,
    @Json(name = "vote_count")
    val vote_count: Int? = null,
    @Json(name = "release_date")
    val release_date: String? = null,
)
