package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.remote.MovieGenre
import com.caner.composemoviedb.presentation.MovieDetailViewModel
import com.caner.composemoviedb.ui.component.RatingBar
import com.caner.composemoviedb.ui.theme.Typography
import com.google.accompanist.coil.rememberCoilPainter
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
                MovieBackdropSection(movieState.data.backdrop?.original){
                    navigateUp()
                }
                MovieTopSection(movieState.data.poster?.original, movieState.data.title ?: "")
                ChipSection(movieState.data.genres)
                Text(
                    modifier = Modifier
                        .offset(y = (-58).dp)
                        .padding(horizontal = 16.dp),
                    lineHeight = 20.sp,
                    text = movieState.data.overview ?: "",
                    fontSize = 14.sp
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
fun MovieBackdropSection(backdrop: String?, navigateUp: () -> Unit) {
    val backdropPoster =
        rememberCoilPainter(
            request = backdrop,
            fadeIn = true
        )

    Box {
        Image(
            painter = backdropPoster,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .alpha(0.9f)
        )

        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navigateUp()
                }
        )
    }
}

@Composable
fun MovieTopSection(poster: String?, title: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        val painter =
            rememberCoilPainter(
                request = poster,
                fadeIn = true
            )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .offset(y = (-90).dp)
                .padding(start = 16.dp)
                .width(120.dp)
                .height(180.dp)
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = Typography.subtitle1
            )

            RatingBar(
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
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .padding(4.dp),
                //   .padding(horizontal = 4.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = genres?.get(it)?.name ?: "",
                    color = Color.White,
                    style = Typography.caption
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}
