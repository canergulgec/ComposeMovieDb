package com.caner.search.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caner.domain.model.Movie
import com.caner.data.provider.SearchScreenDataProvider
import com.caner.search.state.SearchUiState
import com.caner.search.state.TextEvent
import com.caner.search.vm.SearchViewModel
import com.caner.ui.theme.ComposeMovieDbTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
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
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
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
            }
        )

        SearchList(uiState = uiState, onMovieClicked = onMovieClicked)
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