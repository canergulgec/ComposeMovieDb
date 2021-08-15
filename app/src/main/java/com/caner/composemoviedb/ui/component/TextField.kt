package com.caner.composemoviedb.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.caner.composemoviedb.ui.theme.Typography

@Composable
fun MovieTextField(
    modifier: Modifier,
    placeHolder: String = "",
    newValue: (String) -> Unit = {}
) {
    var query by rememberSaveable { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = {
            query = it
            newValue(it)
        },
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        singleLine = true,
        textStyle = Typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(0.5.dp, Color.Black, RoundedCornerShape(8.dp))
    )
}