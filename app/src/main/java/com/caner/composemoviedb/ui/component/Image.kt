package com.caner.composemoviedb.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun MoviePoster(poster: String?, modifier: Modifier) {
    val painter =
        rememberImagePainter(
            data = poster,
            builder  = {
                crossfade(true)
               //  placeholder(R.drawable.placeholder)
               //  transformations(CircleCropTransformation())
            }
        )

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
    )
}