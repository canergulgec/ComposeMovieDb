package com.caner.composemoviedb.view.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caner.composemoviedb.view.main.NavActions
import kotlinx.coroutines.delay


@Composable
fun SearchScreen(actions: NavActions) {
    LaunchedEffect(key1 = true) {
        delay(2500L)
        actions.popBackStack.invoke()
       // actions.gotoDashboard.invoke()
    }
}

@Composable
fun LottieAnimationPlaceHolder(lottie: Int, message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      /*  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottie))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
        )
        LottieAnimation(
            modifier = Modifier.size(280.dp),
            composition = composition,
            progress = progress
        )

        Text(
            text = message, style = MaterialTheme.typography.button,
            fontSize = 18.sp
        )*/
    }
}