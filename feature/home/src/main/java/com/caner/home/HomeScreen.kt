package com.caner.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.caner.common.R
import coil.request.ImageRequest
import com.caner.home.vm.HomeViewModel
import com.caner.ui.composables.FullScreenLoading
import com.caner.ui.composables.MovieRatingComponent
import com.caner.ui.composables.ViewContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.padding
import com.caner.model.Movie

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    onMovieClicked: (Int) -> Unit
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
                    onMovieClicked = onMovieClicked
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
    onMovieClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(140.dp),
        contentPadding = innerPadding,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = movies, key = { it.movieId }) { movie ->
            NowPlayingMovieItem(item = movie, onClicked = onMovieClicked)
        }
    }
}

@Composable
fun NowPlayingMovieItem(item: Movie, onClicked: (Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClicked(item.movieId)
                },
            horizontalAlignment = CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.poster?.original)
                    .crossfade(300)
                    .build(),
                error = painterResource(R.drawable.bg_image_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(horizontal = 8.dp),
                text = item.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            MovieRatingComponent(voteAverage = item.voteAverage, size = 20.dp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

