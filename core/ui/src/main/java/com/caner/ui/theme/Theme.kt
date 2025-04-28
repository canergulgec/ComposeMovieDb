package com.caner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = DARK_WHITE,
    background = Black1,
    surface = Black2, // cards toolbar
    onPrimary = Color.White, // Text vs
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.DarkGray
)

private val LightColorPalette = lightColorScheme(
    primary = Purple200,
    secondary = Black2,
    background = DIRTY_WHITE,
    surface = DARK_WHITE,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.DarkGray
)

@Composable
fun ComposeMovieDbTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}