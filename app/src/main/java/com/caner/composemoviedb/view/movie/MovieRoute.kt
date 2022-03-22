package com.caner.composemoviedb.view.movie

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.caner.composemoviedb.R
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.presentation.viewmodel.MovieViewModel
import com.caner.composemoviedb.ui.component.*
import com.caner.composemoviedb.view.main.NavActions
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
fun MovieRoute(
    navActions: NavActions,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val nowPlayingMovies = viewModel.nowPlayingMoviesPagingFlow.collectAsLazyPagingItems()
    val popularMovieState by viewModel.popularMovieUiState.collectAsState()

    fun navigateTo(movieId: Int) {
        navActions.gotoDetail.invoke(movieId)
    }
    LoadingContent(
        loading = popularMovieState.isFetchingMovies,
        loadingContent = { FullScreenLoading() },
        content = {
            LazyColumn(
                contentPadding = PaddingValues(top = 24.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                nowPlayingMovies(data = nowPlayingMovies, ::navigateTo)
                popularMovies(data = popularMovieState.popularMovies, ::navigateTo)
            }
        }
    )
}

fun LazyListScope.nowPlayingMovies(data: LazyPagingItems<Movie>, onClicked: (Int) -> Unit) {
    item {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.now_playing),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(data) { movieItem ->
                movieItem?.let {
                    MovieItem(item = it, onClicked = onClicked)
                }
            }

            data.loadState.apply {
                if (append is LoadState.Loading) {
                    item { LoadingItem() }
                }
            }
        }
    }
}

fun LazyListScope.popularMovies(data: List<Movie>, onClicked: (Int) -> Unit) {
    item {
        Text(
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
            text = stringResource(id = R.string.popular),
            style = MaterialTheme.typography.h6
        )

        HorizontalPager(
            count = data.size,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val movie = data[page]
            Card(
                Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.90f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .aspectRatio(1.7f)
                    .clickable {
                        onClicked(movie.movieId)
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.small)
                ) {
                    CustomImage(image = movie.backdrop?.original, fadeDuration = 300)
                    Text(
                        text = movie.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(BottomStart)
                            .padding(16.dp)
                            .background(
                                Color.Black.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(item: Movie, onClicked: (Int) -> Unit) {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small,
        contentColor = Color.LightGray,
        modifier = Modifier.width(140.dp)
    ) {
        Column(horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .clickable {
                    onClicked(item.movieId)
                }
        ) {
            CustomImage(
                image = item.poster?.medium,
                fadeDuration = 300,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.caption,
            )
            MovieRating(voteAverage = item.voteAverage, size = 20.dp)
        }
    }
}

