package com.caner.composemoviedb.features.composables

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caner.composemoviedb.R
import com.caner.composemoviedb.features.ui.theme.ComposeMovieDbTheme

@Composable
fun CircularProgressComponent(
    voteAverage: Double,
    total: Int,
    fontSize: TextUnit = 12.sp,
    radius: Dp = 20.dp,
    color: Color = colorResource(id = R.color.gold),
    strokeWidth: Dp = 2.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animPlayed by remember {
        mutableStateOf(false)
    }
    val percentage = (voteAverage / 10).toFloat()

    val curPercentage = animateFloatAsState(
        targetValue = if (animPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = voteAverage) {
        animPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2)
    ) {
        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(
                color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = "% ${(curPercentage.value * total).toInt()}",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CircularProgressComponentPreview() {
    ComposeMovieDbTheme {
        CircularProgressComponent(voteAverage = 5.0, total = 100)
    }
}