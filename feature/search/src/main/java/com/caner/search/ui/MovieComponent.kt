package com.caner.search.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.caner.model.Movie
import com.caner.common.R
import com.caner.ui.composables.MovieRatingChip
import com.caner.ui.theme.ComposeMovieDbTheme

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
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSecondary,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                MovieRatingChip(rating = item.voteAverage)
                Text(text = "|", style = MaterialTheme.typography.bodySmall)
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = item.releaseDate ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, backgroundColor = 0x00000, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieComponentPreview() {
    ComposeMovieDbTheme {
        MovieComponent(
            item = Movie(
                movieId = 438148,
                title = "Minions: The Rise of Gru",
                voteAverage = 8.0,
                releaseDate = "1994-07-14",
                poster = null,
                backdrop = null,
                popularity = 4.5,
                overview = "",
            ),
            itemClicked = {

            }
        )
    }
}
