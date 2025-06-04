package com.caner.composemoviedb.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.caner.common.R
import com.caner.common.Constants
import com.caner.composemoviedb.ui.screen.MainViewModel
import com.caner.detail.ui.MovieDetailScreen
import com.caner.home.ui.HomeScreen
import com.caner.composemoviedb.navigation.BottomNavItem
import com.caner.composemoviedb.navigation.NavigationDirections
import com.caner.composemoviedb.navigation.NavigationManager
import com.caner.search.ui.SearchScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun AppNavigation(changeTheme: () -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val navManager = remember(navController) { NavigationManager(navController) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        val shouldShowBottomBar = when (currentRoute) {
            NavigationDirections.Detail.route -> false
            else -> true
        }
        viewModel.changeBottomBarVisibility(shouldShowBottomBar)
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = viewModel.bottomBarVisibility.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavigationBar(navController = navController, navigationManager = navManager)
            }
        },
        floatingActionButton = {
            ThemeToggleButton(onThemeChange = changeTheme)
        }
    ) { paddingValues ->
        AppNavHost(navController = navController, navManager = navManager, paddingValues = paddingValues)
    }
}

@Composable
private fun BottomNavigationBar(
    navController: NavController,
    navigationManager: NavigationManager
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination

        BottomNavItem.entries.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title)
                    )
                },
                label = {
                    Text(text = stringResource(id = item.title))
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onClick = { navigationManager.navigateToBottomNavItem(item) }
            )
        }
    }
}

@Composable
fun ThemeToggleButton(onThemeChange: () -> Unit) {
    FloatingActionButton(
        onClick = onThemeChange,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        val isDarkTheme = isSystemInDarkTheme()
        Icon(
            painter = painterResource(
                id = if (isDarkTheme) R.drawable.ic_day else R.drawable.ic_dark
            ),
            contentDescription = "Change Theme"
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun AppNavHost(navController: NavHostController, navManager: NavigationManager, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier.fillMaxWidth(),
        navController = navController,
        startDestination = NavigationDirections.Home.route
    ) {
        composable(NavigationDirections.Home.route) {
            HomeScreen(
                innerPadding = paddingValues,
                onOpenMovieDetail = navManager::navigateToDetail
            )
        }
        composable(NavigationDirections.Search.route) {
            SearchScreen(
                innerPadding = paddingValues,
                onOpenMovieDetail = navManager::navigateToDetail
            )
        }

        composable(
            route = NavigationDirections.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_ID) {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            MovieDetailScreen(onBackPressed = navManager::navigateUp)
        }
    }
}