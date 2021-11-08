package com.caner.composemoviedb.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caner.composemoviedb.R
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.viewmodel.SearchViewModel
import com.caner.composemoviedb.ui.component.CircularProgress
import com.caner.composemoviedb.ui.component.CustomSearchBar
import com.caner.composemoviedb.ui.component.MoviePoster
import com.caner.composemoviedb.ui.state.TextEvent
import com.caner.composemoviedb.ui.theme.Dimens
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun SearchScreen(
    navActions: NavActions,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val contentState = viewModel.searchContent.value
        CustomSearchBar(
            text = contentState.text,
            isHintVisible = contentState.isHintVisible,
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

        SearchList(navActions)
    }
}

@FlowPreview
@Composable
fun SearchList(
    navActions: NavActions,
    viewModel: SearchViewModel = hiltViewModel()
) {
    when (val result = viewModel.searchFlow.collectAsState(initial = Resource.Initial).value) {
        is Resource.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 70.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(result.data.movies) { item ->
                    SearchItem(item) { movieId ->
                        navActions.gotoDetail(movieId)
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
            }
        }
        is Resource.Loading -> {
            CircularProgressIndicator()
        }
        is Resource.Initial, Resource.Empty -> {
            MovieTypes()
        }

        is Resource.Error -> {

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
        MoviePoster(
            poster = item.poster?.original,
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
            CircularProgress(percentage = ((item.voteAverage) / 10).toFloat(), number = 100)
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
        cells = GridCells.Fixed(2),
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