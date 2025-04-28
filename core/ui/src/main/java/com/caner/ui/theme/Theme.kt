package com.caner.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    val targetColorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val animationSpec = TweenSpec<Color>(durationMillis = 500) // Adjust duration as needed

    val primary by animateColorAsState(targetColorScheme.primary, animationSpec)
    val secondary by animateColorAsState(targetColorScheme.secondary, animationSpec)
    val background by animateColorAsState(targetColorScheme.background, animationSpec)
    val surface by animateColorAsState(targetColorScheme.surface, animationSpec)
    val onPrimary by animateColorAsState(targetColorScheme.onPrimary, animationSpec)
    val onSecondary by animateColorAsState(targetColorScheme.onSecondary, animationSpec)
    val onBackground by animateColorAsState(targetColorScheme.onBackground, animationSpec)
    val onSurface by animateColorAsState(targetColorScheme.onSurface, animationSpec)
    // Add other colors if needed (error, tertiary, etc.)

    val animatedColorScheme = if (darkTheme) {
        darkColorScheme(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = surface,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface
            // Add other animated colors
        )
    } else {
        lightColorScheme(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = surface,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface
            // Add other animated colors
        )
    }

    MaterialTheme(
        colorScheme = animatedColorScheme,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}