package com.caner.composemoviedb.navigation

sealed class NavigationDirections(var route: String) {
    data object Home : NavigationDirections(route = "movie")
    data object Search : NavigationDirections("search")
    data object Detail : NavigationDirections("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
