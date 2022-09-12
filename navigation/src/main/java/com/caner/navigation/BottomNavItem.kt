package com.caner.navigation

import androidx.annotation.StringRes

enum class BottomNavItem(val route: String, val icon: Int, @StringRes val title: Int) {
    MOVIE(Routes.Home.route, R.drawable.ic_movie, R.string.screen_name_movie),
    SEARCH(Routes.Search.route, R.drawable.ic_search, R.string.screen_name_search)
}