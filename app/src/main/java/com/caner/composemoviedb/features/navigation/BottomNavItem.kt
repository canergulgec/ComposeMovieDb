package com.caner.composemoviedb.features.navigation

import androidx.annotation.StringRes
import com.caner.composemoviedb.R

enum class BottomNavItem(val route: String, val icon: Int, @StringRes val title: Int) {
    MOVIE(NavScreen.Movie.route, R.drawable.ic_movie, R.string.screen_name_movie),
    SEARCH(NavScreen.Search.route, R.drawable.ic_search, R.string.screen_name_search)
}