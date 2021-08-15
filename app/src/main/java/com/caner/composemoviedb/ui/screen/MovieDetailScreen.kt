package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.MovieDetailModel
import com.caner.composemoviedb.data.remote.MovieGenre
import com.caner.composemoviedb.presentation.MovieDetailViewModel
import com.caner.composemoviedb.ui.component.MoviePoster
import com.caner.composemoviedb.ui.component.RatingBar
import com.caner.composemoviedb.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun DetailScreen(
    movieId: String?,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    // We only want the event stream to be attached once
    // even if there are multiple re-compositions
    LaunchedEffect(true) {
        viewModel.getMovieDetail(movieId?.toInt())
    }

    when (val movieState = viewModel.movieDetailState.collectAsState().value) {
        is Resource.Success -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MovieTopSection(movieState.data) {
                    navigateUp()
                }
                ChipSection(movieState.data.genres)
                Text(
                    modifier = Modifier
                        .offset(y = (-58).dp)
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary,
                    lineHeight = 20.sp,
                    text = movieState.data.overview,
                )
            }
        }

        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Error -> {
        }

        is Resource.Empty -> {
        }
    }
}

@Composable
fun MovieTopSection(data: MovieDetailModel, navigateUp: () -> Unit) {
    Column {
        MovieBackdropSection(data.backdrop?.original) {
            navigateUp()
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            MoviePoster(
                poster = data.poster?.original, modifier = Modifier
                    .offset(y = (-90).dp)
                    .padding(start = 16.dp)
                    .width(120.dp)
                    .height(180.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onPrimary
                )

                RatingBar(
                    range = 1..5,
                    isLargeRating = false,
                    isSelectable = false,
                    currentRating = (data.voteAverage / 2).toInt()
                )
            }
        }
    }
}

@Composable
fun MovieBackdropSection(backdrop: String?, navigateUp: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
    ) {
        MoviePoster(
            poster = backdrop, modifier = Modifier
                .fillMaxSize()
                .alpha(0.85f)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            BLACK_TRANSPARENT,
                            BLACK_TRANSPARENT_60
                        )
                    )
                )
        )

        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .clickable {
                    navigateUp()
                }
        )
    }
}

@Composable
fun ChipSection(genres: List<MovieGenre>?) {
    FlowRow(
        modifier = Modifier
            .offset(y = (-74).dp)
            .padding(horizontal = 16.dp)
    ) {
        repeat(genres?.size ?: 0) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.Transparent)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = genres?.get(it)?.name ?: "",
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
