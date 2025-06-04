package com.caner.ui.composables

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.caner.common.R

@Composable
fun MoviePosterComponent(
    posterUrl: String,
    modifier: Modifier = Modifier,
    aspectRatio: Float? = null,
    cornerRadius: Dp = 0.dp,
    crossFadeMillis: Int = 300,
    enableMemoryCache: Boolean = true,
    enableDiskCache: Boolean = true
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterUrl)
            .crossfade(crossFadeMillis)
            .memoryCachePolicy(
                if (enableMemoryCache) CachePolicy.ENABLED else CachePolicy.DISABLED
            )
            .diskCachePolicy(
                if (enableDiskCache) CachePolicy.ENABLED else CachePolicy.DISABLED
            )
            .build(),
        placeholder = painterResource(R.drawable.bg_image_placeholder),
        error = painterResource(R.drawable.bg_image_placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .then(if (aspectRatio != null) Modifier.aspectRatio(aspectRatio) else Modifier)
            .clip(RoundedCornerShape(cornerRadius))
    )
}