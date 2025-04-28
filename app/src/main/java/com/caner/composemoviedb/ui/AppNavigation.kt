package com.caner.composemoviedb.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
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
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalLifecycleComposeApi
@FlowPreview
@Composable
fun AppNavigation(changeTheme: () -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = viewModel.bottomBarVisibility.value) {
                BottomNavigationBar(
                    controller = navController,
                    onNavigationSelected = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        floatingActionButton = {
            FloatingButton {
                changeTheme()
            }
        }
    ) {
        Navigation(navController = navController, modifier = Modifier.padding(it))
    }

    when (currentRoute(navController = navController)) {
        NavigationDirections.Detail.route -> viewModel.changeBottomBarVisibility(false)
        else -> viewModel.changeBottomBarVisibility(true)
    }
}

@Composable
fun FloatingButton(
    isLightTheme: Boolean = MaterialTheme.colors.isLight,
    changeTheme: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            changeTheme()
        },
        backgroundColor = colorResource(id = R.color.purple_200)
    ) {
        Icon(
            painter = when (isLightTheme) {
                true -> painterResource(id = R.drawable.ic_dark)
                false -> painterResource(id = R.drawable.ic_day)
            },
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun BottomNavigationBar(
    controller: NavHostController,
    onNavigationSelected: (BottomNavItem) -> Unit,
    modifier: Modifier
) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        modifier = modifier
    ) {
        BottomNavItem.entries.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.title)
                    )
                },
                label = { Text(text = stringResource(id = screen.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                selectedContentColor = colorResource(id = R.color.purple_200),
                unselectedContentColor = Color.LightGray,
                onClick = { onNavigationSelected(screen) },
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalLifecycleComposeApi
@FlowPreview
@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {
    val navManager = remember(navController) { NavigationManager(navController) }

    NavHost(navController, startDestination = NavigationDirections.Home.route, modifier = modifier) {
        composable(NavigationDirections.Home.route) {
            HomeScreen(
                viewModel = hiltViewModel(),
                onMovieClicked = { movieID ->
                    navManager.gotoDetail(movieID)
                }
            )
        }
        composable(NavigationDirections.Search.route) {
            SearchScreen(
                viewModel = hiltViewModel(),
                onMovieClicked = { movieID ->
                    navManager.gotoDetail(movieID)
                }
            )
        }

        composable(
            route = NavigationDirections.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_ID) {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            MovieDetailScreen(
                viewModel = hiltViewModel(),
                onBackPressed = navManager.upPress
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}