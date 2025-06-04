package com.caner.model

import androidx.compose.ui.graphics.Color

data class MovieCategory(
    val title: String,
    val subtitle: String,
    val searchQuery: String,
    val gradientColors: List<Color>
)

sealed class MovieCategories {
    companion object {
        val all = listOf(
            MovieCategory(
                title = "New Releases",
                subtitle = "Latest movies",
                searchQuery = "2024",
                gradientColors = listOf(Color(0xFF6366F1), Color(0xFF8B5CF6))
            ),
            MovieCategory(
                title = "Award Winners",
                subtitle = "Oscar & Emmy",
                searchQuery = "Oscar",
                gradientColors = listOf(Color(0xFFEF4444), Color(0xFFF59E0B))
            ),
            MovieCategory(
                title = "Blockbusters",
                subtitle = "Box office hits",
                searchQuery = "Marvel",
                gradientColors = listOf(Color(0xFF10B981), Color(0xFF06B6D4))
            ),
            MovieCategory(
                title = "Classics",
                subtitle = "Timeless films",
                searchQuery = "Classic",
                gradientColors = listOf(Color(0xFF8B5CF6), Color(0xFFEC4899))
            )
        )
    }
} 