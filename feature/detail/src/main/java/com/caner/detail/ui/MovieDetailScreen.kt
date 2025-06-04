package com.caner.detail.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
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
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.caner.detail.ui.enum.InfoChipStyle
import com.caner.detail.ui.enum.InfoChipType

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

            targetState.movieDetailData != null -> {
                MovieDetailContent(movie = targetState.movieDetailData, onBackPressed = onBackPressed)
            }
        }
    }
}

@Composable
fun MovieDetailContent(
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
                .fillMaxHeight(0.7f)
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
                    MovieTitleSection(movie = movie)
                    Spacer(modifier = Modifier.height(8.dp))
                    MovieGenreComponent(genres = movie.genres)
                    Spacer(modifier = Modifier.height(16.dp))
                    MovieOverviewSection(overview = movie.overview)
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
                .height(300.dp),
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
                        colors = listOf(Color.Black.copy(alpha = 0.3f), Color.Black.copy(alpha = 0.7f))
                    )
                )
        )
        Surface(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp)
                .align(Alignment.TopStart)
                .size(48.dp),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f),
            onClick = onBackPressed
        ) {
            Icon(
                modifier = Modifier.padding(12.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

@Composable
fun MovieTitleSection(movie: MovieDetailModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MovieRatingChip(rating = movie.voteAverage, voteCount = movie.voteCount)
            InfoChip(
                icon = Icons.Default.Face,
                text = "${movie.runtime} ${stringResource(id = R.string.minutes)}",
                type = InfoChipType.RUNTIME
            )
            movie.releaseDate?.let { releaseDate ->
                InfoChip(
                    icon = Icons.Default.DateRange,
                    text = releaseDate,
                    type = InfoChipType.RELEASE_DATE
                )
            }
        }
    }
}

@Composable
private fun InfoChip(
    icon: ImageVector,
    text: String,
    type: InfoChipType,
    style: InfoChipStyle = InfoChipStyle.FILLED
) {
    val (backgroundColor, contentColor, borderColor) = when (type) {
        InfoChipType.RUNTIME -> Triple(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            MaterialTheme.colorScheme.secondary
        )

        InfoChipType.RELEASE_DATE -> Triple(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.onPrimaryContainer,
            MaterialTheme.colorScheme.primary
        )
    }

    when (style) {
        InfoChipStyle.FILLED -> {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = backgroundColor,
                modifier = Modifier.padding(horizontal = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium,
                        color = contentColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        InfoChipStyle.OUTLINED -> {
            Surface(
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, borderColor.copy(alpha = 0.6f)),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium,
                        color = contentColor
                    )
                }
            }
        }

        InfoChipStyle.GRADIENT -> {
            val gradientColors = when (type) {
                InfoChipType.RUNTIME -> listOf(
                    Color(0xFF6366F1),
                    Color(0xFF8B5CF6)
                )

                InfoChipType.RELEASE_DATE -> listOf(
                    Color(0xFF10B981),
                    Color(0xFF059669)
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        InfoChipStyle.MINIMAL -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = backgroundColor.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                    color = contentColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun MovieGenreComponent(genres: List<MovieGenre>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(genres) { genre ->
            AssistChip(
                onClick = { },
                label = {
                    Text(
                        text = genre.name,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    }
}

@Composable
private fun MovieOverviewSection(overview: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = stringResource(R.string.overview),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.animateContentSize(),
            text = overview,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 20.sp,
            maxLines = if (isExpanded) Int.MAX_VALUE else 4,
            overflow = TextOverflow.Ellipsis
        )

        if (overview.length > 200) {
            TextButton(
                modifier = Modifier.padding(top = 4.dp),
                onClick = { isExpanded = !isExpanded },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                border = BorderStroke(width = 0.5.dp, color = MaterialTheme.colorScheme.outline)
            ) {
                Text(
                    text = if (isExpanded) stringResource(R.string.show_less) else stringResource(R.string.show_more)
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
        MovieDetailContent(movie = movie, onBackPressed = {})
    }
}