package com.caner.composemoviedb.features.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caner.composemoviedb.R
import com.caner.composemoviedb.domain.model.MovieDetailModel
import com.caner.composemoviedb.domain.model.remote.MovieGenre
import com.caner.composemoviedb.data.provider.MovieDetailDataProvider
import com.caner.composemoviedb.features.composables.*
import com.caner.composemoviedb.features.navigation.NavActions
import com.caner.composemoviedb.features.screen.detail.vm.MovieDetailViewModel
import com.caner.composemoviedb.features.ui.theme.BLACK_TRANSPARENT
import com.caner.composemoviedb.features.ui.theme.BLACK_TRANSPARENT_60
import com.caner.composemoviedb.features.ui.theme.ComposeMovieDbTheme
import com.caner.composemoviedb.features.ui.theme.Dimens
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalLifecycleComposeApi
@Composable
fun MovieDetailScreen(
    navActions: NavActions,
    viewModel: MovieDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movieModel = uiState.movieDetailModel

    ViewContent(
        isLoading = uiState.isFetchingMovieDetail,
        hasError = uiState.hasError,
        loadingContent = { FullScreenLoading() },
        errorContent = {
            LottieAnimationComponent(
                anim = R.raw.error_anim,
                message = stringResource(id = R.string.default_error_message)
            )
        },
        content = {
            movieModel?.let {
                MovieDetailUi(movieModel = it, onBackPressed = {
                    navActions.upPress()
                })
            }
        }
    )
}

@Composable
fun MovieDetailUi(
    movieModel: MovieDetailModel,
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieBackdropComponent(
            poster = movieModel.backdrop?.original,
            onBackPressed = { onBackPressed() })

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(horizontal = Dimens.MediumPadding.size)) {
                MovieTitleComponent(
                    modifier = Modifier.padding(vertical = 16.dp),
                    movie = movieModel
                )
                MovieGenreComponent(genres = movieModel.genres)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary,
                    lineHeight = 20.sp,
                    text = movieModel.overview,
                )
            }
        }
    }
}

@Composable
fun MovieBackdropComponent(poster: String?, onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.55f)
    ) {
        ImageComponent(
            modifier = Modifier.fillMaxSize(),
            image = poster,
            fadeDuration = 300
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
            modifier = Modifier
                .padding(Dimens.MediumPadding.size)
                .align(Alignment.TopStart)
                .clickable {
                    onBackPressed()
                },
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null
        )
    }
}

@Composable
fun MovieTitleComponent(modifier: Modifier = Modifier, movie: MovieDetailModel) {
    Column(modifier = modifier) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            MovieRatingComponent(voteAverage = movie.voteAverage, size = 20.dp)
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
fun MovieGenreComponent(genres: List<MovieGenre>) {
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

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MovieDetailUiPreview(
    @PreviewParameter(MovieDetailDataProvider::class) movie: MovieDetailModel
) {
    ComposeMovieDbTheme {
        MovieDetailUi(movieModel = movie, onBackPressed = {})
    }
}