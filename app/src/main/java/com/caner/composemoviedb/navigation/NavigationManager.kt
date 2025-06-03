package com.caner.composemoviedb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class NavigationManager(private val navController: NavController) {

    fun navigateUp(): Boolean = navController.navigateUp()

    fun navigateToDetail(movieId: Int) {
        if (movieId > 0) {
            navController.navigate(NavigationDirections.Detail.createRoute(movieId))
        }
    }

    fun navigateToBottomNavItem(item: BottomNavItem) {
        navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}