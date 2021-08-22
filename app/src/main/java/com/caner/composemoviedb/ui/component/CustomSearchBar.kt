package com.caner.composemoviedb.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.caner.composemoviedb.ui.theme.Dimens
import com.caner.composemoviedb.ui.theme.MovieItemComposeTheme
import com.caner.composemoviedb.R
import com.caner.composemoviedb.ui.theme.DIRTY_WHITE

@ExperimentalComposeUiApi
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search_hint),
    onSearch: (String) -> Unit = { },
    onDismissSearchClicked: () -> Unit = { },
    keyboardController: SoftwareKeyboardController?
    = LocalSoftwareKeyboardController.current,
    focusManager: FocusManager = LocalFocusManager.current
) {
    var searchText by rememberSaveable {
        mutableStateOf("")
    }

    var isHintActive by rememberSaveable {
        mutableStateOf(hint.isNotEmpty())
    }
    var isTyping by rememberSaveable {
        mutableStateOf(false)
    }

    val paddingSize: Dp by animateDpAsState(
        targetValue = if (isHintActive) {
            Dimens.MediumPadding.size
        } else 4.dp
    )

    val angle: Float by animateFloatAsState(
        targetValue = if (isHintActive) -90F else 0F,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )
    val searchAndOptionsAngle: Float by animateFloatAsState(
        targetValue = if (isHintActive) 0F else 90F,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    val hintAlpha: Float by animateFloatAsState(
        targetValue = if (isHintActive) 1F else 0.5f
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = paddingSize)
            .fillMaxWidth()
            .background(
                color = DIRTY_WHITE,
                shape = MaterialTheme.shapes.large
            )
            .border(width = 0.5.dp, color = Color.Gray, shape = MaterialTheme.shapes.large)
    ) {
        Box(contentAlignment = Alignment.CenterStart) {
            if (isHintActive) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .padding(Dimens.MediumPadding.size)
                        .rotate(searchAndOptionsAngle)
                )
            } else {
                IconButton(
                    onClick = {
                        onDismissSearchClicked()
                        searchText = ""
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        isTyping = searchText.isNotBlank()
                    },
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_icon),
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier.rotate(angle)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(vertical = Dimens.SmallPadding.size)
            ) {
                if (!isTyping) {
                    Text(
                        text = hint,
                        color = MaterialTheme.colors.onSurface
                            .copy(alpha = hintAlpha),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                BasicTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearch(it)
                        isTyping = searchText.isNotBlank()
                    },
                    maxLines = 1,
                    cursorBrush = SolidColor(MaterialTheme.colors.primary),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.subtitle1
                        .copy(color = MaterialTheme.colors.onSurface),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            if (searchText.isBlank()) {
                                focusManager.clearFocus()
                            }
                        }
                    ),
                    modifier = Modifier
                        // .fillMaxWidth()
                        .onFocusChanged {
                            isHintActive = !it.isFocused
                        }
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                if (!isHintActive) {
                    IconButton(
                        onClick = {
                            onDismissSearchClicked()
                            searchText = ""
                            isTyping = searchText.isNotBlank()
                        },
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.clear_icon),
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.rotate(angle)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
private fun CustomSearchBarPreview() {
    MovieItemComposeTheme {
        CustomSearchBar()
    }
}