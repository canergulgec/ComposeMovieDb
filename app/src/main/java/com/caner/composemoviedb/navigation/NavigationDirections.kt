package com.caner.composemoviedb.navigation

sealed class NavigationDirections(var route: String) {
    object Home : NavigationDirections(route = "movie")
    object Search : NavigationDirections("search")
    object Detail : NavigationDirections("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
