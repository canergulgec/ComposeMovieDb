package com.caner.navigation

import androidx.annotation.StringRes

enum class BottomNavItem(val route: String, val icon: Int, @StringRes val title: Int) {
    MOVIE(route = NavigationDirections.Home.route, icon = R.drawable.ic_movie, title = R.string.screen_name_movie),
    SEARCH(route = NavigationDirections.Search.route, icon = R.drawable.ic_search, title = R.string.screen_name_search)
}