package com.caner.search.model

import androidx.annotation.DrawableRes
import com.caner.common.R

data class SearchSuggestion(
    val query: String,
    @DrawableRes val icon: Int,
    val category: String
)

object SearchSuggestions {
    val all = listOf(
        SearchSuggestion(
            query = "Spider-Man",
            icon = R.drawable.ic_spider,
            category = "Superhero"
        ),
        SearchSuggestion(
            query = "DC",
            icon = R.drawable.ic_batman,
            category = "Universe"
        ),
        SearchSuggestion(
            query = "Horror",
            icon = R.drawable.ic_horror,
            category = "Genre"
        ),
        SearchSuggestion(
            query = "Comedy",
            icon = R.drawable.ic_comedy,
            category = "Genre"
        ),
        SearchSuggestion(
            query = "Fantasy",
            icon = R.drawable.ic_fantasy,
            category = "Genre"
        )
    )
}