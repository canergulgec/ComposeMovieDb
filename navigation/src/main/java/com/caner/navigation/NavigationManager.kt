package com.caner.navigation

import androidx.navigation.NavController

class NavigationManager(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoDetail: (Int) -> Unit = { movieId ->
        navController.navigate(NavigationDirections.Detail.createRoute(movieId))
    }
}