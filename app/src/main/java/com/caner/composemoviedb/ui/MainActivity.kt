package com.caner.composemoviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.caner.composemoviedb.MovieApp
import com.caner.composemoviedb.ui.screen.BottomNavigationBar
import com.caner.composemoviedb.ui.screen.FloatingButton
import com.caner.composemoviedb.ui.screen.Navigation
import com.caner.composemoviedb.ui.theme.MovieItemComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var app: MovieApp

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieItemComposeTheme(darkTheme = app.isDark.value) {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = if (app.isDark.value) {
                            Color.DarkGray
                        } else {
                            Color.LightGray
                        },
                        darkIcons = true
                    )
                }

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) },
                    floatingActionButton = {
                        val rippleExplode = remember { mutableStateOf(false) }
                        FloatingButton(rippleExplode, app)
                        if (rippleExplode.value) {

                        }
                    },
                    content = {
                        Navigation(navController = navController)
                    }
                )
            }
        }
    }
}