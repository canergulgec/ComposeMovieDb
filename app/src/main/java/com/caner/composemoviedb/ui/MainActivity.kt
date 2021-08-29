package com.caner.composemoviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.caner.composemoviedb.MovieApp
import com.caner.composemoviedb.ui.screen.*
import com.caner.composemoviedb.ui.theme.MovieItemComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var app: MovieApp

    @ExperimentalPagerApi
    @ExperimentalComposeUiApi
    @ExperimentalCoroutinesApi
    @FlowPreview
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieItemComposeTheme(darkTheme = app.isDark.value) {
                StatusBarColor(app.isDark.value)
                Home(app.isDark.value) {
                    app.changeTheme()
                }
            }
        }
    }
}