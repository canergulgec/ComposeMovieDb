package com.caner.composemoviedb.features.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.caner.composemoviedb.R
import com.caner.composemoviedb.features.ui.theme.ComposeMovieDbTheme

@Composable
fun MovieRatingComponent(voteAverage: Double, size: Dp) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            tint = colorResource(id = R.color.gold),
            contentDescription = null,
            modifier = Modifier.size(size)
        )
        Text(
            text = voteAverage.toString(),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieRatingComponentPreview() {
    ComposeMovieDbTheme {
        MovieRatingComponent(voteAverage = 4.0, size = 20.dp)
    }
}