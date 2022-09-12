package com.caner.navigation

sealed class Routes(var route: String) {
    object Home : Routes("movie")
    object Search : Routes("search")
    object Detail : Routes("detail/{movieId}"){
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
