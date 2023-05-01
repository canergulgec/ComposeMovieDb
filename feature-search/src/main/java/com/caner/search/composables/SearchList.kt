package com.caner.search.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.caner.search.state.SearchUiState
import com.caner.ui.composables.ViewContent
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun SearchList(
    uiState: SearchUiState,
    onMovieClicked: (Int) -> Unit
) {
    when (uiState) {
        is SearchUiState.NoMovies -> {
            EmptyListComponent()
        }
        is SearchUiState.HasMovies -> {
            ViewContent(
                isLoading = uiState.isFetchingMovies,
                loadingContent = { CircularProgressIndicator() },
                content = {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.movies) { item ->
                            MovieComponent(
                                item = item,
                                itemClicked = onMovieClicked
                            )
                            Divider(
                                modifier = Modifier.padding(top = 8.dp),
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