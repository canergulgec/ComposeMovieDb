package com.caner.search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.caner.domain.model.Movie
import com.caner.common.R
import com.caner.ui.composables.MovieRatingComponent

@Composable
fun MovieComponent(
    item: Movie,
    itemClicked: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                itemClicked(item.movieId)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(60.dp)
                .height(90.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.poster?.medium)
                .crossfade(300)
                .transformations(RoundedCornersTransformation(radius = 8f))
                .build(),
            error = painterResource(R.drawable.bg_image_placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.title,
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSecondary,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                MovieRatingComponent(voteAverage = item.voteAverage, size = 16.dp)
                Text(text = "|", style = MaterialTheme.typography.caption)
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = item.releaseDate ?: "",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}