package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import com.caner.composemoviedb.MovieApp
import com.caner.composemoviedb.R
import com.caner.composemoviedb.common.Constants
import com.caner.composemoviedb.utils.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Composable
fun FloatingButton(rippleExplode: MutableState<Boolean>, app: MovieApp) {
    FloatingActionButton(
        onClick = {
            app.changeTheme()
            rippleExplode.value = !rippleExplode.value

        },
        backgroundColor = colorResource(id = R.color.purple_200)
    ) {
        Icon(
            painter = if (app.isDark.value) {
                painterResource(id = R.drawable.ic_day)
            } else {
                painterResource(id = R.drawable.ic_night)
            },
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Movie,
        Screen.Search
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = colorResource(id = R.color.purple_200),
                //unselectedContentColor = colorResource(id = R.color.purple_200).copy(0.4f),
                unselectedContentColor = Color.LightGray,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselect the same item
                        launchSingleTop = true
                        // Restore state when reselect a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Movie.route) {
        composable(Screen.Movie.route) {
            MovieScreen(
                openMovieDetail = { movieId ->
                    navController.navigate(Screen.Detail.createRoute(movieId))
                }
            )
        }
        composable(Screen.Search.route) {
            SearchScreen(
                openMovieDetail = { movieId ->
                    navController.navigate(Screen.Detail.createRoute(movieId))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_ID) {
                type = NavType.StringType
            })
        ) {
            val movieId = it.arguments?.getString(Constants.MOVIE_ID)
            DetailScreen(movieId,
                navigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}
