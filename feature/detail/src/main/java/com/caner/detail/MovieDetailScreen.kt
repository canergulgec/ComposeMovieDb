package com.caner.detail

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.caner.model.MovieDetailModel
import com.caner.model.remote.MovieGenre
import com.caner.ui.preview.MovieDetailDataProvider
import com.caner.detail.vm.MovieDetailViewModel
import com.caner.common.R
import com.caner.ui.composables.*
import com.caner.ui.theme.BLACK_TRANSPARENT
import com.caner.ui.theme.BLACK_TRANSPARENT_60
import com.caner.ui.theme.ComposeMovieDbTheme
import com.caner.ui.theme.Dimens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = uiState,
        transitionSpec = {
            fadeIn(animationSpec = tween(700)) togetherWith
                fadeOut(animationSpec = tween(300))
        },
        label = "DetailScreenAnimation"
    ) { targetState ->
        when {
            targetState.isFetchingMovieDetail -> {
                FullScreenLoading()
            }

            targetState.hasError -> {
                LottieAnimationComponent(
                    anim = R.raw.error_anim,
                    message = stringResource(id = R.string.default_error_message)
                )
            }

            targetState.movieDetailModel != null -> {
                MovieDetailUi(movie = targetState.movieDetailModel, onBackPressed = onBackPressed)
            }
        }
    }
}

@Composable
fun MovieDetailUi(
    movie: MovieDetailModel,
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieBackdropComponent(
            poster = movie.backdrop?.original,
            onBackPressed = { onBackPressed() })

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter),
            shape = MaterialTheme.shapes.large,
            tonalElevation = 8.dp
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.MediumPadding.size),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    MovieTitleComponent(movie = movie)
                    MovieGenreComponent(genres = movie.genres)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        lineHeight = 20.sp,
                        text = movie.overview
                    )
                }
            }
        }
    }
}

@Composable
fun MovieBackdropComponent(poster: String?, onBackPressed: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f),
            model = poster,
            placeholder = painterResource(R.drawable.bg_image_placeholder),
            error = painterResource(R.drawable.bg_image_placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop
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
                .statusBarsPadding()
                .padding(Dimens.MediumPadding.size)
                .align(Alignment.TopStart)
                .clickable {
                    onBackPressed()
                },
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
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
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun MovieGenreComponent(genres: List<MovieGenre>) {
    FlowRow {
        genres.forEach { genre ->
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
                    text = genre.name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodySmall
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
        MovieDetailUi(movie = movie, onBackPressed = {})
    }
}