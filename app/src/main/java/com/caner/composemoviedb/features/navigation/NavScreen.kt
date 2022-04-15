package com.caner.composemoviedb.features.navigation

import com.caner.composemoviedb.R

sealed class NavScreen(var route: String, var icon: Int, var title: String) {
    object Movie : NavScreen("movie", R.drawable.ic_movie, "Movie")
    object Search : NavScreen("search", R.drawable.ic_search, "Search")
    object Detail : AppNavigation("detail/{movieId}"){
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

sealed class AppNavigation(val route: String)