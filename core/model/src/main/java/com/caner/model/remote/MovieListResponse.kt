package com.caner.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "total_pages")
    val total: Int = 0,
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    var results: List<MovieDto>
)