package com.caner.model

data class MovieImage(val url: String?) {
    companion object {
        private const val PATH = "https://image.tmdb.org/t/p"
    }

    val small: String = "$PATH/w92/$url"
    val medium: String = "$PATH/w185/$url"
    val large: String = "$PATH/w342/$url"
    val original: String = "$PATH/original/$url"
}