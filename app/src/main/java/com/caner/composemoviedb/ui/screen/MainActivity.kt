package com.caner.composemoviedb.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.lifecycleScope
import com.caner.composemoviedb.navigation.AppNavigation
import com.caner.ui.theme.ComposeMovieDbTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalLifecycleComposeApi
@FlowPreview
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val darkMode by viewModel.themeManager.uiModeFlow.collectAsState(initial = isSystemInDarkTheme())
            val toggleTheme: () -> Unit = {
                viewModel.setDarkModeEnabled(!darkMode)
            }

            ComposeMovieDbTheme(darkTheme = darkMode) {
                SetStatusBarColor()
                AppNavigation(toggleTheme)
            }
        }
        observeThemeMode()
    }

    private fun observeThemeMode() {
        lifecycleScope.launchWhenStarted {
            viewModel.themeManager.uiModeFlow.collect {
                val mode = when (it) {
                    true -> AppCompatDelegate.MODE_NIGHT_YES
                    false -> AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }
    }
}

@Composable
fun SetStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.surface

    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = true
        )
    }
}