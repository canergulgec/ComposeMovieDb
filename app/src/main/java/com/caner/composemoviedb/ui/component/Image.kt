package com.caner.composemoviedb.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.caner.composemoviedb.R

@Composable
fun CustomImage(
    image: String?,
    modifier: Modifier = Modifier,
    description: String = "",
    fadeDuration: Int = 0
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(fadeDuration)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = description,
        contentScale = ContentScale.FillHeight,
        modifier = modifier
    )
}