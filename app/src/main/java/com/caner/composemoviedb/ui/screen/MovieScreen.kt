package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.caner.composemoviedb.R
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.Movie
import com.caner.composemoviedb.presentation.MovieViewModel
import com.caner.composemoviedb.ui.component.MoviePoster
import com.caner.composemoviedb.ui.state.ErrorItem
import com.caner.composemoviedb.ui.state.ErrorView
import com.caner.composemoviedb.ui.state.LoadingItem
import com.caner.composemoviedb.ui.state.LoadingView

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    openMovieDetail: (String) -> Unit
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
    val lazyMovieItems = viewModel.moviePagingFlow.collectAsLazyPagingItems()
    if (lazyMovieItems.itemCount > 0) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = MaterialTheme.typography.h6
        )
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(lazyMovieItems) {
            MovieItem(it) { movieId ->
                openMovieDetail(movieId)
            }
        }

        lazyMovieItems.apply {
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
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
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

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    tint = colorResource(id = R.color.gold),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = item?.voteAverage.toString(),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PopularMovies(viewModel: MovieViewModel = hiltViewModel()) {
    when (val movieState = viewModel.popularMovieState.collectAsState().value) {
        is Resource.Success -> {
            Text(
                modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                text = stringResource(id = R.string.popular),
                style = MaterialTheme.typography.h6
            )

            LazyRow(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 70.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(movieState.data.movies) {
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .aspectRatio(1.6f)
                            .clip(MaterialTheme.shapes.small)
                            .border(0.5.dp, Color.LightGray, MaterialTheme.shapes.small)
                    ) {
                        MoviePoster(poster = it.backdrop?.original, modifier = Modifier.alpha(0.9f))
                        Text(
                            text = it.title, color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(BottomStart)
                                .padding(16.dp)
                                .background(Color.DarkGray)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
        else -> {
        }
    }
}
