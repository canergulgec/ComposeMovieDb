package com.caner.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ViewContent(
    isLoading: Boolean,
    hasError: Boolean = false,
    loadingContent: @Composable () -> Unit,
    errorContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    when {
        hasError -> errorContent?.invoke()
        isLoading -> loadingContent()
        else -> content()
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
