package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.caner.composemoviedb.data.Movie
import com.caner.composemoviedb.ui.component.RatingBar
import com.caner.composemoviedb.ui.theme.Typography
import com.caner.composemoviedb.presentation.MovieViewModel
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun MovieScreen(openMovieDetail: (String) -> Unit) {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
        //.verticalScroll(state = scrollState)
    ) {
        MovieList(openMovieDetail)
    }
}

@Composable
fun MovieList(
    openMovieDetail: (String) -> Unit,
    title: String = "In Theaters",
    viewModel: MovieViewModel = hiltViewModel()
) {
    val pagingList = viewModel.moviePagingFlow.collectAsLazyPagingItems()

    Text(modifier = Modifier.padding(16.dp), text = title, style = Typography.h6)
    LazyRow {
        items(pagingList) {
            MovieItem(it) { movieId ->
                openMovieDetail(movieId)
            }
        }
    }
}

@Composable
fun MovieItem(item: Movie?, click: (String) -> Unit) {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        contentColor = Color.LightGray,
        modifier = Modifier
            .width(170.dp)
            .padding(8.dp)
    ) {
        Column(horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .clickable {
                    click(item?.movieId.toString())
                }
        ) {

            MoviePoster(item?.poster?.original)
            Text(
                text = item?.title ?: "",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSecondary,
                style = Typography.subtitle2
            )

            RatingBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                range = 0..5,
                isLargeRating = false,
                isSelectable = false,
                currentRating = 2
            ) {

            }
        }
    }
}

@Composable
fun MoviePoster(poster: String?) {
    val painter =
        rememberCoilPainter(
            request = poster,
            fadeIn = true
        )

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .height(220.dp)
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
            //   .clip(MaterialTheme.shapes.medium)
        )

        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                // Display a circular progress indicator whilst loading
                CircularProgressIndicator(
                    modifier = Modifier
                        .scale(0.5f)
                        .align(Center)
                )
            }
            is ImageLoadState.Error -> {
                // If you wish to display some content if the request fails
            }
            else -> {
            }
        }
    }
}
