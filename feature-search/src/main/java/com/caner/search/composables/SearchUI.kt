package com.caner.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.caner.search.state.SearchUiState
import com.caner.ui.theme.Dimens
import kotlinx.coroutines.FlowPreview

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
