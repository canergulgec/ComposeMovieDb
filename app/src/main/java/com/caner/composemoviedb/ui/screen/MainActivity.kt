package com.caner.composemoviedb.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.caner.composemoviedb.ui.AppNavigation
import com.caner.ui.theme.ComposeMovieDbTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val darkMode by viewModel.themeManager.uiModeFlow.collectAsState(initial = isSystemInDarkTheme())
            val toggleTheme: () -> Unit = {
                viewModel.setDarkModeEnabled(!darkMode)
            }

            ComposeMovieDbTheme(darkTheme = darkMode) {
                AppNavigation(toggleTheme)
            }
        }
        observeThemeMode()
    }

    private fun observeThemeMode() {
        viewModel.themeManager.uiModeFlow.onEach {
            val mode = when (it) {
                true -> AppCompatDelegate.MODE_NIGHT_YES
                false -> AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }.launchIn(lifecycleScope)
    }
}