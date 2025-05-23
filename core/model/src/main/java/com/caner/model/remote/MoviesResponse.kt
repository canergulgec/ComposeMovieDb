package com.caner.model.remote

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("total_pages", alternate = ["total_results"])
    val total: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    var results: List<MovieResponseItem>
)