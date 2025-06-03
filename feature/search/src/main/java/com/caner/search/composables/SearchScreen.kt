package com.caner.search.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caner.model.Movie
import com.caner.ui.preview.SearchScreenDataProvider
import com.caner.search.state.SearchUiState
import com.caner.search.state.TextEvent
import com.caner.search.vm.SearchViewModel
import com.caner.ui.composables.ViewContent
import com.caner.ui.theme.ComposeMovieDbTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    onOpenMovieDetail: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreenUi(
        uiState = uiState,
        innerPadding = PaddingValues(
            top = innerPadding.calculateTopPadding() + 16.dp,
            bottom = innerPadding.calculateBottomPadding() + 16.dp,
        ),
        onOpenMovieDetail = onOpenMovieDetail,
        onUIEvent = {
            viewModel.onEvent(it)
        }
    )
}

@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchScreenUi(
    uiState: SearchUiState,
    innerPadding: PaddingValues,
    onOpenMovieDetail: (Int) -> Unit,
    onUIEvent: (TextEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarComponent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            text = uiState.searchTitle,
            isHintVisible = uiState.isHintVisible,
            onValueChange = {
                onUIEvent(TextEvent.OnValueChange(it))
            },
            onFocusChange = {
                onUIEvent(TextEvent.OnFocusChange(it))
            },
            onDismissClicked = {
                onUIEvent(TextEvent.OnValueChange(""))
            }
        )

        SearchList(
            uiState = uiState,
            innerPadding = innerPadding,
            onMovieClicked = onOpenMovieDetail
        )
    }
}

@FlowPreview
@Composable
fun SearchList(
    uiState: SearchUiState,
    innerPadding: PaddingValues,
    onMovieClicked: (Int) -> Unit
) {
    ViewContent(
        isLoading = uiState.isLoading,
        loadingContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        content = {
            if (uiState.movies.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 24.dp,
                        bottom = innerPadding.calculateBottomPadding()
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.movies) { item ->
                        MovieComponent(
                            item = item,
                            itemClicked = onMovieClicked
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(top = 8.dp),
                            color = Color.LightGray,
                            thickness = 0.5.dp
                        )
                    }
                }
            } else {
                EmptyListComponent()
            }
        }
    )
}

@ExperimentalComposeUiApi
@FlowPreview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenPreview(
    @PreviewParameter(SearchScreenDataProvider::class) movies: List<Movie>
) {
    val searchUiState = SearchUiState(
        movies = movies,
        searchTitle = "Minions",
        isHintVisible = false
    )
    ComposeMovieDbTheme {
        SearchScreenUi(
            uiState = searchUiState,
            innerPadding = PaddingValues(),
            onOpenMovieDetail = {},
            onUIEvent = {}
        )
    }
}