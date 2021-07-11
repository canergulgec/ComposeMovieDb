package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.caner.composemoviedb.ui.component.RatingBar
import com.caner.composemoviedb.ui.theme.Typography
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun DetailScreen(navController: NavController) {
    Column {
        MovieBackdrop(navController)
        Row {
            val painter =
                rememberCoilPainter(
                    request = "https://image.tmdb.org/t/p/w500/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                    fadeIn = true
                )

            Image(
                painter = painter,
                contentDescription = "MovieName",
                modifier = Modifier
                    .offset(y = (-90).dp)
                    .padding(start = 16.dp)
                    .width(120.dp)
                    .height(180.dp)

            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Movie Name",
                    style = Typography.subtitle1
                )

                RatingBar(
                    range = 0..5,
                    isLargeRating = false,
                    isSelectable = false,
                    currentRating = 2
                ) {

                }
            }
        }

        ChipSection()
        Text(
            modifier = Modifier
                .offset(y = (-58).dp)
                .padding(horizontal = 16.dp),
            lineHeight = 20.sp,
            text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing",
            fontSize = 14.sp
        )
    }
}

@Composable
fun MovieBackdrop(navController: NavController) {
    val backdrop =
        rememberCoilPainter(
            request = "https://image.tmdb.org/t/p/original//9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
            fadeIn = true
        )

    Box {
        Image(
            painter = backdrop,
            contentScale = ContentScale.Crop,
            contentDescription = "MovieName",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .alpha(0.9f)
        )

        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
fun ChipSection() {
    FlowRow(
        modifier = Modifier
            .offset(y = (-74).dp)
            .padding(horizontal = 16.dp)
    ) {
        repeat(6) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .padding(4.dp),
                //   .padding(horizontal = 4.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Horror",
                    color = Color.White,
                    style = Typography.caption
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}
