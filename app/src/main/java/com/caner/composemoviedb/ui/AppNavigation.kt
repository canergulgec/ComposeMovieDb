package com.caner.composemoviedb.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.caner.common.R
import com.caner.common.Constants
import com.caner.composemoviedb.ui.screen.MainViewModel
import com.caner.detail.MovieDetailScreen
import com.caner.home.HomeScreen
import com.caner.composemoviedb.navigation.BottomNavItem
import com.caner.composemoviedb.navigation.NavigationDirections
import com.caner.composemoviedb.navigation.NavigationManager
import com.caner.search.composables.SearchScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun AppNavigation(changeTheme: () -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = viewModel.bottomBarVisibility.value) {
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    BottomNavItem.entries.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painterResource(id = screen.icon),
                                    contentDescription = stringResource(id = screen.title)
                                )
                            },
                            label = { Text(text = stringResource(id = screen.title)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = colorResource(id = R.color.purple_200),
                                selectedTextColor = colorResource(id = R.color.purple_200),
                                unselectedIconColor = Color.LightGray,
                                unselectedTextColor = Color.LightGray,
                                indicatorColor = MaterialTheme.colorScheme.surface
                            ),
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            SetThemeFloatingButton {
                changeTheme()
            }
        }
    ) { paddingValues ->
        Timber.v("padding values: $paddingValues")
        NavHostComponent(navController = navController)
    }

    when (currentRoute(navController = navController)) {
        NavigationDirections.Detail.route -> viewModel.changeBottomBarVisibility(false)
        else -> viewModel.changeBottomBarVisibility(true)
    }
}

@Composable
fun SetThemeFloatingButton(
    changeTheme: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            changeTheme()
        },
        containerColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    ) {
        val isLightTheme = !isSystemInDarkTheme()
        Icon(
            painter = when (isLightTheme) {
                true -> painterResource(id = R.drawable.ic_dark)
                false -> painterResource(id = R.drawable.ic_day)
            },
            contentDescription = "Change Theme"
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun NavHostComponent(navController: NavHostController) {
    val navManager = remember(navController) { NavigationManager(navController) }

    NavHost(
        navController = navController,
        startDestination = NavigationDirections.Home.route
    ) {
        composable(NavigationDirections.Home.route) {
            HomeScreen(onMovieClicked = { movieID -> navManager.gotoDetail(movieID) })
        }
        composable(NavigationDirections.Search.route) {
            SearchScreen(onMovieClicked = { movieID -> navManager.gotoDetail(movieID) })
        }

        composable(
            route = NavigationDirections.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_ID) {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            MovieDetailScreen(onBackPressed = navManager.upPress)
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}