package com.caner.composemoviedb.ui.navigation

import com.caner.composemoviedb.R

sealed class Screen(var route: String, var icon: Int, var title: String) {
    object Movie : Screen("movie", R.drawable.ic_movie, "Movie")
    object Search : Screen("search", R.drawable.ic_search, "Search")
    object Detail : AppNavigation("detail/{movieId}"){
        fun createRoute(movieId: String) = "detail/$movieId"
    }
}

sealed class AppNavigation(val route: String)