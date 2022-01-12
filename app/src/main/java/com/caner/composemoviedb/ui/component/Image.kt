package com.caner.composemoviedb.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.caner.composemoviedb.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MoviePhoto(poster: String?, modifier: Modifier = Modifier) {
    GlideImage(
        imageModel = poster,
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Crop,
        // shows an image with a circular revealed animation.
        // circularReveal = CircularReveal(duration = 250),
        // shows a placeholder ImageBitmap when loading.
        placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
        modifier = modifier
        // shows an error ImageBitmap when the request failed.
        //error = ImageBitmap.imageResource(R.drawable.error)
    )

}