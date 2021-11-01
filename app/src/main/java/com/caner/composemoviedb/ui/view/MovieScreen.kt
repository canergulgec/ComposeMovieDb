package com.caner.composemoviedb.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.caner.composemoviedb.R
import com.caner.composemoviedb.domain.viewstate.Resource
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.viewmodel.MovieViewModel
import com.caner.composemoviedb.ui.component.MoviePoster
import com.caner.composemoviedb.ui.component.MovieRating
import com.caner.composemoviedb.ui.state.ErrorItem
import com.caner.composemoviedb.ui.state.ErrorView
import com.caner.composemoviedb.ui.state.LoadingItem
import com.caner.composemoviedb.ui.state.LoadingView
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
fun MovieScreen(
    openMovieDetail: (String) -> Unit,
    viewModel: MovieViewModel = hiltViewModel()
) {
    // We only want the event stream to be attached once
    // even if there are multiple re-compositions
    LaunchedEffect(true) {
        viewModel.getPopularMovies()
    }

    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(state = scrollState)
    ) {
        NowPlayingMovies(openMovieDetail)
        Spacer(modifier = Modifier.height(16.dp))
        PopularMovies()
    }
}

@Composable
fun NowPlayingMovies(
    openMovieDetail: (String) -> Unit,
    title: String = stringResource(id = R.string.now_playing),
    viewModel: MovieViewModel = hiltViewModel()
) {
    var showTitle by remember { mutableStateOf(false) }
    if (showTitle) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = MaterialTheme.typography.h6
        )
    }

    val nowPlayingMovieList = viewModel.moviePagingFlow.collectAsLazyPagingItems()
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(nowPlayingMovieList) {
            showTitle = true
            MovieItem(it) { movieId ->
                openMovieDetail(movieId)
            }
        }

        nowPlayingMovieList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingView(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(top = 16.dp)
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = nowPlayingMovieList.loadState.refresh as LoadState.Error
                    item {
                        ErrorView(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        ErrorItem(
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(item: Movie?, click: (String) -> Unit) {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small,
        contentColor = Color.LightGray,
        modifier = Modifier.width(150.dp)
    ) {
        Column(horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .clickable {
                    click(item?.movieId.toString())
                }
        ) {
            MoviePoster(
                item?.poster?.original, modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .height(200.dp)
            )

            Text(
                text = item?.title ?: "",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.caption,
            )

            MovieRating(voteAverage = item?.voteAverage.toString(), size = 20.dp)
        }
    }
}

@Composable
fun PopularMovies(viewModel: MovieViewModel = hiltViewModel()) {
    when (val result =
        viewModel.popularMovieState.collectAsState(initial = Resource.Initial).value) {
        is Resource.Success -> {
            Text(
                modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                text = stringResource(id = R.string.popular),
                style = MaterialTheme.typography.h6
            )

            PopularMoviesHorizontalPager(result.data.movies)
        }
        else -> {
        }
    }
}

@Composable
fun PopularMoviesHorizontalPager(movies: List<Movie>) {
    HorizontalPager(
        count = 10,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val movie = movies[page]
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
                .aspectRatio(1.6f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.small)
            ) {
                MoviePoster(poster = movie.backdrop?.original)
                Text(
                    text = movie.title, color = Color.White,
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
