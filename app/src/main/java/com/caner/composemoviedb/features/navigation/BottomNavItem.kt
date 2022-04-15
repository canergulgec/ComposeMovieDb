package com.caner.composemoviedb.features.navigation

import com.caner.composemoviedb.R
import com.caner.composemoviedb.utils.Constants

enum class BottomNavItem(var route: String, var icon: Int, var title: String) {
    MOVIE(NavScreen.Movie.route, R.drawable.ic_movie, Constants.SCREEN_NAME_MOVIE),
    SEARCH(NavScreen.Search.route, R.drawable.ic_search, Constants.SCREEN_NAME_SEARCH)
}