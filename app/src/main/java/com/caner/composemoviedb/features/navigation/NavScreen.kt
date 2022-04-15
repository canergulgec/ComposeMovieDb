package com.caner.composemoviedb.features.navigation

sealed class NavScreen(var route: String) {
    object Movie : NavScreen("movie")
    object Search : NavScreen("search")
    object Detail : AppNavigation("detail/{movieId}"){
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

sealed class AppNavigation(val route: String)