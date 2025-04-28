package com.caner.search.composables

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.caner.ui.theme.Dimens
import com.caner.common.R
import com.caner.ui.theme.ComposeMovieDbTheme
import com.caner.ui.theme.DIRTY_WHITE

@ExperimentalComposeUiApi
@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    text: String = "",
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit = {},
    onDismissClicked: () -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    keyboardController: SoftwareKeyboardController?
    = LocalSoftwareKeyboardController.current,
    focusManager: FocusManager = LocalFocusManager.current
) {

    var isTyping = text.isNotEmpty()

    val paddingSize: Dp by animateDpAsState(
        targetValue = if (isHintVisible) {
            Dimens.UpperMediumPadding.size
        } else {
            Dimens.MediumPadding.size
        }
    )

    val angle: Float by animateFloatAsState(
        targetValue = if (isHintVisible) -90F else 0F,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )
    val searchAndOptionsAngle: Float by animateFloatAsState(
        targetValue = if (isHintVisible) 0F else 90F,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    val hintAlpha: Float by animateFloatAsState(
        targetValue = if (isHintVisible) 1F else 0.6f
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
            if (isHintVisible) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(Dimens.MediumPadding.size)
                        .rotate(searchAndOptionsAngle)
                )
            } else {
                IconButton(
                    onClick = {
                        onDismissClicked()
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        isTyping = text.isNotBlank()
                    },
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_icon),
                        tint = MaterialTheme.colorScheme.onSurface,
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
                        text = stringResource(id = R.string.search_hint),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = hintAlpha),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                BasicTextField(
                    value = text,
                    onValueChange = {
                        onValueChange(it)
                        isTyping = text.isNotBlank()
                    },
                    maxLines = 1,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            if (text.isBlank()) {
                                focusManager.clearFocus()
                            }
                        }
                    ),
                    modifier = Modifier
                        .onFocusChanged {
                            onFocusChange(!it.isFocused)
                        }
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                if (!isHintVisible) {
                    IconButton(
                        onClick = {
                            onDismissClicked()
                            isTyping = text.isNotBlank()
                        },
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.clear_icon),
                            tint = MaterialTheme.colorScheme.onSurface,
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
    ComposeMovieDbTheme {
        SearchBarComponent()
    }
}