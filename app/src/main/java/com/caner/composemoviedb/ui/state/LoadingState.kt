package com.caner.composemoviedb.ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.imageloading.ImageLoadState
import com.google.accompanist.imageloading.LoadPainter

@Composable
fun LoadingImageState(
    painter: LoadPainter<Any>,
    modifier: Modifier = Modifier
) {
    when (painter.loadState) {
        is ImageLoadState.Loading -> {
            // Display a circular progress indicator whilst loading
            CircularProgressIndicator(
                modifier = modifier
            )
        }
        is ImageLoadState.Error -> {
            // If you wish to display some content if the request fails
        }
        else -> {
        }
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    )
}
