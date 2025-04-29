package com.caner.model.remote

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    @SerializedName("poster_path")
    val poster_path: String? = null,
    @SerializedName("genres")
    val genres: List<MovieGenre>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("imdb_id")
    val imdb_id: String? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("vote_average")
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    val vote_count: Int? = null,
    @SerializedName("release_date")
    val release_date: String? = null,
)
