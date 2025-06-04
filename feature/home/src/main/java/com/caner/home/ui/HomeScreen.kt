package com.caner.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caner.home.vm.HomeViewModel
import com.caner.ui.composables.FullScreenLoading
import com.caner.ui.composables.MovieRatingChip
import com.caner.ui.composables.ViewContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.padding
import com.caner.model.Movie
import com.caner.ui.theme.ComposeMovieDbTheme
import android.content.res.Configuration
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.caner.ui.composables.MoviePosterComponent
import com.caner.ui.preview.HomeDataProvider

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    onOpenMovieDetail: (Int) -> Unit
) {
    val movieViewState by viewModel.movieUiState.collectAsStateWithLifecycle()

    ViewContent(
        isLoading = movieViewState.isLoading,
        loadingContent = { FullScreenLoading() },
        content = {
            if (movieViewState.popularMovies.isNotEmpty()) {
                MoviesGridComponent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    movies = movieViewState.popularMovies,
                    innerPadding = PaddingValues(
                        top = innerPadding.calculateTopPadding() + 16.dp,
                        bottom = innerPadding.calculateBottomPadding() + 16.dp,
                    ),
                    onOpenMovieDetail = onOpenMovieDetail
                )
            }
        }
    )
}

@Composable
fun MoviesGridComponent(
    modifier: Modifier,
    movies: List<Movie>,
    innerPadding: PaddingValues,
    onOpenMovieDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(140.dp),
        contentPadding = innerPadding,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = movies, key = { it.movieId }) { movie ->
            MovieGridItem(movie = movie, onClicked = onOpenMovieDetail)
        }
    }
}

@Composable
fun MovieGridItem(movie: Movie, onClicked: (Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = { onClicked(movie.movieId) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            movie.poster?.let { poster ->
                MoviePosterComponent(
                    posterUrl = poster.original,
                    modifier = Modifier.fillMaxSize(),
                    aspectRatio = 2f / 3f,
                    crossFadeMillis = 400
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            MovieInfo(
                title = movie.title,
                rating = movie.voteAverage,
                voteCount = movie.voteCount
            )
        }
    }
}

@Composable
fun MovieInfo(title: String, rating: Double, voteCount: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 8.dp),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        MovieRatingChip(rating = rating, voteCount = voteCount)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFF)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0x000000)
@Composable
private fun HomeScreenMoviesGridPreview(
    @PreviewParameter(HomeDataProvider::class) movies: List<Movie>
) {
    ComposeMovieDbTheme {
        MoviesGridComponent(
            modifier = Modifier.padding(horizontal = 16.dp),
            movies = movies,
            innerPadding = PaddingValues(all = 16.dp),
            onOpenMovieDetail = {}
        )
    }
}
