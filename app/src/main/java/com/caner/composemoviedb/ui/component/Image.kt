package com.caner.composemoviedb.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun MoviePoster(poster: String?, modifier: Modifier) {
    val painter =
        rememberCoilPainter(
            request = poster,
            fadeIn = true
        )

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
    )
}