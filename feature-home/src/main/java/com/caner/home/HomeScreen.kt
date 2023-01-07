package com.caner.home

import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.caner.domain.model.Movie
import com.caner.home.vm.HomeViewModel
import com.caner.ui.composables.FullScreenLoading
import com.caner.ui.composables.MovieRatingComponent
import com.caner.ui.composables.ViewContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalLifecycleComposeApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMovieClicked: (Int) -> Unit
) {
    val movieViewState by viewModel.movieUiState.collectAsStateWithLifecycle()
    val movieLazyItems = movieViewState.nowPlayingMovies?.collectAsLazyPagingItems()

    ViewContent(
        isLoading = movieViewState.isFetchingMovies && movieLazyItems?.loadState?.refresh is LoadState.Loading,
        loadingContent = { FullScreenLoading() },
        content = {
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(top = 24.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    movieLazyItems?.let { movies ->
                        item {
                            MainContainer(
                                title = R.string.now_playing,
                                content = {
                                    NowPlayingMovies(data = movies, onClicked = onMovieClicked)
                                }
                            )
                        }
                    }
                    item {
                        MainContainer(
                            title = R.string.popular,
                            content = {
                                PopularMovies(
                                    data = movieViewState.popularMovies,
                                    onClicked = onMovieClicked
                                )
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(title),
            style = MaterialTheme.typography.h6
        )
        content()
    }
}

@Composable
fun NowPlayingMovies(data: LazyPagingItems<Movie>, onClicked: (Int) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(data) { movieItem ->
            movieItem?.let {
                NowPlayingMovieItem(item = it, onClicked = onClicked)
            }
        }

        data.loadState.apply {
            if (append is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun PopularMovies(data: List<Movie>, onClicked: (Int) -> Unit) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = data.size,
        contentPadding = PaddingValues(horizontal = 32.dp)
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
            PopularMovieItem(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        Color.Black.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                movie = movie
            )
        }
    }
}

@Composable
fun NowPlayingMovieItem(item: Movie, onClicked: (Int) -> Unit) {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small,
        contentColor = Color.LightGray,
    ) {
        Column(horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .width(140.dp)
                .clickable {
                    onClicked(item.movieId)
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
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
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.caption,
            )
            Spacer(modifier = Modifier.height(8.dp))
            MovieRatingComponent(voteAverage = item.voteAverage, size = 20.dp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PopularMovieItem(modifier: Modifier, movie: Movie) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.backdrop?.original)
                .crossfade(300)
                .transformations(RoundedCornersTransformation(radius = 8f))
                .build(),
            error = painterResource(R.drawable.bg_image_placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = modifier.align(BottomStart),
            text = movie.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

