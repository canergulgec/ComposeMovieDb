package com.caner.composemoviedb.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.caner.composemoviedb.R
import com.caner.composemoviedb.data.Constants
import com.caner.composemoviedb.ui.navigation.Screen
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun Home(changeTheme: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) != Screen.Detail.route) {
                BottomNavigationBar(
                    controller = navController,
                    onNavigationSelected = { screen ->
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselect the same item
                            launchSingleTop = true
                            // Restore state when reselect a previously selected item
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
        //Navigation(navController = navController, modifier = Modifier.padding(innerPadding))
        Navigation(navController = navController, modifier = Modifier)
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
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(
    controller: NavHostController,
    onNavigationSelected: (Screen) -> Unit,
    modifier: Modifier
) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        modifier = modifier
    ) {
        HomeNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) },
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

    NavHost(navController, startDestination = Screen.Movie.route, modifier = modifier) {
        composable(Screen.Movie.route) {
            MovieScreen(actions)
        }
        composable(Screen.Search.route) {
            SearchScreen(actions)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_ID) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(actions)
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

private val HomeNavigationItems = listOf(
    Screen.Movie,
    Screen.Search
)

class NavActions(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val gotoDetail: (Int) -> Unit = { movieId ->
        navController.navigate(Screen.Detail.createRoute(movieId))
    }
}