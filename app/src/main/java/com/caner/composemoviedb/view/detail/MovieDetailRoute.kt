package com.caner.composemoviedb.view.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.data.model.remote.MovieGenre
import com.caner.composemoviedb.presentation.viewmodel.MovieDetailViewModel
import com.caner.composemoviedb.ui.component.FullScreenLoading
import com.caner.composemoviedb.ui.component.LoadingContent
import com.caner.composemoviedb.ui.component.MoviePhoto
import com.caner.composemoviedb.ui.component.MovieRating
import com.caner.composemoviedb.ui.theme.*
import com.caner.composemoviedb.view.main.NavActions
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailRoute(
    navActions: NavActions,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    // We only want the event stream to be attached once
    // even if there are multiple re-compositions
    LaunchedEffect(true) {
        viewModel.navActions = navActions
    }

    val uiState by viewModel.uiState.collectAsState()
    val movieModel = uiState.movieDetailModel

    LoadingContent(
        loading = uiState.isFetchingMovieDetail,
        loadingContent = { FullScreenLoading() },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                movieModel?.let {
                    MovieTopSection(it)
                    ChipSection(it.genres)
                    Text(
                        modifier = Modifier
                            .offset(y = (-58).dp)
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary,
                        lineHeight = 20.sp,
                        text = it.overview,
                    )
                }
            }
        }
    )
}

@Composable
fun MovieTopSection(data: MovieDetailModel) {
    Column {
        MovieBackdropSection(data.backdrop?.original)
        Row(modifier = Modifier.fillMaxWidth()) {
            MoviePhoto(
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    MovieRating(voteAverage = data.voteAverage.toString(), size = 20.dp)
                    Text(
                        text = "${data.runtime} min",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MovieBackdropSection(backdrop: String?, viewModel: MovieDetailViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
    ) {
        MoviePhoto(poster = backdrop, modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            BLACK_TRANSPARENT_60,
                            BLACK_TRANSPARENT
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
                    viewModel.navActions.upPress.invoke()
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