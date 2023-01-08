package com.caner.search

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.caner.domain.model.Movie
import com.caner.data.provider.SearchScreenDataProvider
import com.caner.search.composables.SearchBarComponent
import com.caner.search.state.SearchUiState
import com.caner.search.state.TextEvent
import com.caner.search.vm.SearchViewModel
import com.caner.ui.composables.CircularProgressComponent
import com.caner.ui.composables.ViewContent
import com.caner.ui.theme.ComposeMovieDbTheme
import com.caner.ui.theme.Dimens
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalLifecycleComposeApi
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onMovieClicked: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SearchScreenUi(
        uiState = uiState,
        onMovieClicked = onMovieClicked,
        onValueChange = {
            viewModel.onEvent(TextEvent.OnValueChange(it))
        },
        onFocusChange = {
            viewModel.onEvent(TextEvent.OnFocusChange(it))
        }
    )
}

@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchScreenUi(
    uiState: SearchUiState,
    onMovieClicked: (Int) -> Unit,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarComponent(
            text = uiState.searchTitle,
            isHintVisible = uiState.isHintVisible,
            onValueChange = {
                onValueChange(it)
            },
            onFocusChange = {
                onFocusChange(it)
            },
            onDismissClicked = {
                onValueChange("")
            },
            modifier = Modifier.padding(
                vertical = Dimens.MediumPadding.size
            )
        )
        SearchList(uiState = uiState, onMovieClicked = onMovieClicked)
    }
}

@FlowPreview
@Composable
fun SearchList(
    uiState: SearchUiState,
    onMovieClicked: (Int) -> Unit
) {
    when (uiState) {
        is SearchUiState.NoMovies -> {
            NoMovieComposable()
        }
        is SearchUiState.HasMovies -> {
            ViewContent(
                isLoading = uiState.isFetchingMovies,
                loadingContent = { CircularProgressIndicator() },
                content = {
                    LazyColumn(
                        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.movies) { item ->
                            MovieItemComposable(
                                item = item,
                                itemClicked = onMovieClicked
                            )
                            Divider(
                                modifier = Modifier.padding(top = 16.dp),
                                color = Color.LightGray,
                                thickness = 0.5.dp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun MovieItemComposable(
    item: Movie,
    itemClicked: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                itemClicked(item.movieId)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .background(color = Color.DarkGray),
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.poster?.medium)
                .crossfade(300)
                .transformations(RoundedCornersTransformation(radius = 8f))
                .build(),
            error = painterResource(R.drawable.bg_image_placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.title,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSecondary,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = item.releaseDate ?: "",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            CircularProgressComponent(voteAverage = item.voteAverage, total = 100)
        }
    }
}

@Composable
fun NoMovieComposable() {
    Text(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
        text = stringResource(id = R.string.movie_types),
        style = MaterialTheme.typography.h6
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 60.dp)
    ) {

        items(listOf(Color.LightGray, 2, 3, 4, 5, 6, 7, 8)) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Horror", color = Color.White,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@FlowPreview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenPreview(
    @PreviewParameter(SearchScreenDataProvider::class) movies: List<Movie>
) {
    val searchUiState = SearchUiState.HasMovies(
        movies = movies,
        isFetchingMovies = false,
        searchTitle = "Minions",
        isHintVisible = false
    )
    ComposeMovieDbTheme {
        SearchScreenUi(
            uiState = searchUiState,
            onMovieClicked = {},
            onValueChange = {},
            onFocusChange = {})
    }
}