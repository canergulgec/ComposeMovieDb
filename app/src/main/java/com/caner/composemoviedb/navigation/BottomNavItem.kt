package com.caner.composemoviedb.navigation

import androidx.annotation.StringRes
import com.caner.common.R

enum class BottomNavItem(val navigationRoute: NavigationDirections, val icon: Int, @StringRes val title: Int) {
    MOVIE(
        navigationRoute = NavigationDirections.Home,
        icon = R.drawable.ic_movie,
        title = R.string.screen_name_movie
    ),
    SEARCH(
        navigationRoute = NavigationDirections.Search,
        icon = R.drawable.ic_search,
        title = R.string.screen_name_search
    );

    val route: String get() = navigationRoute.route
}