package com.caner.composemoviedb.features.screen.search

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caner.composemoviedb.R
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.features.component.*
import com.caner.composemoviedb.features.navigation.NavActions
import com.caner.composemoviedb.features.screen.search.state.TextEvent
import com.caner.composemoviedb.features.ui.theme.Dimens
import com.caner.composemoviedb.features.screen.search.state.SearchUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalLifecycleComposeApi
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchScreen(
    navActions: NavActions,
    viewModel: SearchViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SearchBarComponent(
            text = uiState.searchTitle,
            isHintVisible = uiState.isHintVisible,
            onValueChange = {
                viewModel.onEvent(TextEvent.OnValueChange(it))
            },
            onFocusChange = {
                viewModel.onEvent(TextEvent.OnFocusChange(it))
            },
            onDismissClicked = {
                viewModel.onEvent(TextEvent.OnValueChange(""))
            },
            modifier = Modifier.padding(
                vertical = Dimens.MediumPadding.size
            )
        )

        SearchList(uiState, navActions)
    }
}

@FlowPreview
@Composable
fun SearchList(
    uiState: SearchUiState,
    navActions: NavActions
) {
    when (uiState) {
        is SearchUiState.NoMovies -> {
            MovieTypes()
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
                            SearchItem(
                                item = item,
                                itemClicked = { movieId -> navActions.gotoDetail(movieId) }
                            )
                            Divider(
                                color = Color.LightGray,
                                thickness = 0.5.dp,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SearchItem(
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
        ImageComponent(
            image = item.poster?.medium,
            fadeDuration = 300,
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.releaseDate ?: "",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize()
            )
            Spacer(modifier = Modifier.height(12.dp))
            CircularProgressComponent(percentage = ((item.voteAverage) / 10).toFloat(), total = 100)
        }
    }
}

@Composable
fun MovieTypes() {
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
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(60.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                Text(
                    text = "Horror", color = Color.White,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}