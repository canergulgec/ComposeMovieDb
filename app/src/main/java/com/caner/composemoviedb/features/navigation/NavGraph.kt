package com.caner.composemoviedb.features.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.caner.composemoviedb.R
import com.caner.composemoviedb.features.screen.detail.MovieDetailRoute
import com.caner.composemoviedb.features.screen.movie.MovieRoute
import com.caner.composemoviedb.features.screen.search.SearchRoute
import com.caner.composemoviedb.utils.Constants
import com.caner.composemoviedb.features.screen.main.MainViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun NavGraph(changeTheme: () -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    ProvideWindowInsets {
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
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
            NavScreen.Detail.route -> viewModel.changeBottomBarVisibility(false)
            else -> viewModel.changeBottomBarVisibility(true)
        }
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
        BottomNavItem.values().forEach { screen ->
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

@FlowPreview
@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {
    val actions = remember(navController) { NavActions(navController) }

    NavHost(navController, startDestination = NavScreen.Movie.route, modifier = modifier) {
        composable(NavScreen.Movie.route) {
            MovieRoute(actions, hiltViewModel())
        }
        composable(NavScreen.Search.route) {
            SearchRoute(actions, hiltViewModel())
        }

        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_ID) {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            MovieDetailRoute(actions, hiltViewModel())
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

class NavActions(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val gotoDetail: (Int) -> Unit = { movieId ->
        navController.navigate(NavScreen.Detail.createRoute(movieId))
    }
}