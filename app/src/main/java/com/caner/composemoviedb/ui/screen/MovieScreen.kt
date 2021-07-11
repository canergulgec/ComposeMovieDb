package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.caner.composemoviedb.ui.component.RatingBar
import com.caner.composemoviedb.ui.theme.Typography
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@ExperimentalFoundationApi
@Composable
fun MovieScreen(openMovieDetail: (String) -> Unit) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        MovieTabView(
            textList = listOf("VİZYONDA", "YAKINDA")
        ) {
            selectedTabIndex = it
        }

        when (selectedTabIndex) {
            0 -> MovieList(openMovieDetail)
        }
    }
}

@Composable
fun MovieTabView(
    modifier: Modifier = Modifier,
    textList: List<String>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val inactiveColor = Color(0xFF777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        textList.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Text(
                    text = textList[index],
                    // color = if (selectedTabIndex == index) MaterialTheme.colors.primary else inactiveColor,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    style = Typography.body2
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieList(openMovieDetail: (String) -> Unit) {
    val movieList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 56.dp)
    ) {
        items(movieList.size) {
            MovieItem { movieId ->
                openMovieDetail(movieId)
            }
        }
    }
}

//******
@Composable
fun MovieItem(click: (String) -> Unit) {
    val painter =
        rememberCoilPainter(
            request = "https://image.tmdb.org/t/p/w500/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg",
            fadeIn = true
        )
    Column(horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable {
                click("movieDetail")
            }
    ) {

        Image(
            painter = painter,
            contentDescription = "MovieName",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                //.width(150.dp)
                // .align(CenterHorizontally)
                .clip(RoundedCornerShape(8.dp))

        )

        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                // Display a circular progress indicator whilst loading
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )
            }
            is ImageLoadState.Error -> {
                // If you wish to display some content if the request fails
            }
            else -> {
            }
        }

        Text(
            text = "Mortal Kombat",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSecondary,
            style = Typography.subtitle2
        )

        RatingBar(
            modifier = Modifier.fillMaxWidth(),
            range = 0..5,
            isLargeRating = false,
            isSelectable = false,
            currentRating = 2
        ) {

        }
    }
}