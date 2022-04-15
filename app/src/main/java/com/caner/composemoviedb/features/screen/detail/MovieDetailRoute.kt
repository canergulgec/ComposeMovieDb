package com.caner.composemoviedb.features.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caner.composemoviedb.R
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.data.model.remote.MovieGenre
import com.caner.composemoviedb.features.component.CustomImage
import com.caner.composemoviedb.features.component.FullScreenLoading
import com.caner.composemoviedb.features.component.LoadingContent
import com.caner.composemoviedb.features.component.MovieRating
import com.caner.composemoviedb.features.navigation.NavActions
import com.caner.composemoviedb.features.ui.theme.BLACK_TRANSPARENT
import com.caner.composemoviedb.features.ui.theme.BLACK_TRANSPARENT_60
import com.caner.composemoviedb.features.ui.theme.Dimens
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailRoute(
    navActions: NavActions,
    viewModel: MovieDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val movieModel = uiState.movieDetailModel

    LoadingContent(
        loading = uiState.isFetchingMovieDetail,
        loadingContent = { FullScreenLoading() },
        content = {
            movieModel?.let {
                Box(modifier = Modifier.fillMaxSize()) {
                    MovieBackdrop(it.backdrop?.original, onBackPressed = {
                        navActions.upPress()
                    })

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .align(Alignment.BottomCenter),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(modifier = Modifier.padding(horizontal = Dimens.MediumPadding.size)) {
                            Spacer(modifier = Modifier.height(16.dp))
                            MovieContent(movie = it)
                            Spacer(modifier = Modifier.height(16.dp))
                            ChipSection(genres = it.genres)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.secondary,
                                lineHeight = 20.sp,
                                text = it.overview,
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun MovieBackdrop(poster: String?, onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.55f)
    ) {
        CustomImage(
            image = poster,
            fadeDuration = 300,
            modifier = Modifier.fillMaxSize()
        )
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
                .padding(Dimens.MediumPadding.size)
                .align(Alignment.TopStart)
                .clickable {
                    onBackPressed()
                }
        )
    }
}

@Composable
fun MovieContent(movie: MovieDetailModel) {
    Column {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            MovieRating(voteAverage = movie.voteAverage, size = 20.dp)
            Spacer(
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding.size)
                    .width(1.dp)
                    .height(16.dp)
                    .background(Color.LightGray)
            )
            Text(
                text = "${movie.runtime} ${stringResource(id = R.string.minutes)}",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
fun ChipSection(genres: List<MovieGenre>) {
    FlowRow {
        repeat(genres.size) { pos ->
            Box(
                modifier = Modifier
                    .padding(Dimens.XSmallPadding.size)
                    .border(1.dp, Color.LightGray, MaterialTheme.shapes.medium)
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.Transparent)
                    .padding(
                        vertical = Dimens.XSmallPadding.size,
                        horizontal = Dimens.SmallPadding.size
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = genres[pos].name,
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
