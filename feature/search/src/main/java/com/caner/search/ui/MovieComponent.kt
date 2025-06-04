package com.caner.search.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.caner.model.Movie
import com.caner.common.R
import com.caner.ui.composables.MovieRatingChip
import com.caner.ui.preview.SearchScreenDataProvider
import com.caner.ui.theme.ComposeMovieDbTheme

@Composable
fun MovieComponent(
    item: Movie,
    itemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { itemClicked(item.movieId) }
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    itemClicked(item.movieId)
                }
                .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item.poster?.let { poster ->
                MoviePosterComponent(posterUrl = poster.medium)
            }
            MovieDetailsSection(movie = item)
        }
    }
}

@Composable
private fun MoviePosterComponent(modifier: Modifier = Modifier, posterUrl: String) {
    AsyncImage(
        modifier = modifier
            .size(width = 70.dp, height = 100.dp)
            .clip(RoundedCornerShape(8.dp)),
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterUrl)
            .crossfade(300)
            .build(),
        error = painterResource(R.drawable.bg_image_placeholder),
        placeholder = painterResource(R.drawable.bg_image_placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun MovieDetailsSection(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(modifier = modifier) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))
        MovieMetadataSection(
            rating = movie.voteAverage,
            voteCount = movie.voteCount,
            releaseDate = movie.releaseDate
        )
        if (movie.overview.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun MovieMetadataSection(
    rating: Double,
    voteCount: Int,
    releaseDate: String?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieRatingChip(rating = rating, voteCount = voteCount)
        releaseDate?.let { date ->
            Text(
                text = "• $date",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, backgroundColor = 0x00000, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieComponentPreview(@PreviewParameter(SearchScreenDataProvider::class) movies: List<Movie>) {
    ComposeMovieDbTheme {
        MovieComponent(
            item = movies.first(),
            itemClicked = {}
        )
    }
}
