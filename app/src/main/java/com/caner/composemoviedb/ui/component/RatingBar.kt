package com.caner.composemoviedb.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caner.composemoviedb.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    range: IntRange,
    isLargeRating: Boolean = true,
    isSelectable: Boolean = true,
    currentRating: Int = 0,
    onRatingChanged: (Int) -> Unit = {}
) {
    val selectedRating = remember {
        mutableStateOf(currentRating)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {

        LazyRow(
            contentPadding = PaddingValues(top = 4.dp)
        ) {
            items(range.toList()) { item ->
                RatingItem(
                    isSelected = item <= selectedRating.value,
                    isSelectable = isSelectable,
                    item,
                    isLargeRating
                ) { newRating ->
                    onRatingChanged(newRating)
                }
            }
        }
    }
}

@Composable
fun RatingItem(
    isSelected: Boolean,
    isSelectable: Boolean,
    rating: Int,
    isLargeRating: Boolean,
    onRatingChanged: (Int) -> Unit
) {

    val padding = if (isLargeRating) 2.dp else 0.dp
    val size = if (isLargeRating) 48.dp else 16.dp

    val baseModifier = if (isSelectable) {
        Modifier.clickable { onRatingChanged(rating) }
    } else {
        Modifier
    }

    Icon(
        if (isSelected) {
            painterResource(id = R.drawable.ic_baseline_star_24)
        } else {
            painterResource(id = R.drawable.ic_baseline_star_outline_24)
        },
        tint = colorResource(id = R.color.gold),
        contentDescription = null,
        modifier = baseModifier
            .padding(padding)
            .size(size)
    )
}