package com.caner.composemoviedb.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.Movie
import com.caner.composemoviedb.presentation.SearchViewModel
import com.caner.composemoviedb.ui.component.CircularProgress
import com.caner.composemoviedb.ui.component.SearchBar
import com.caner.composemoviedb.ui.theme.Typography
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    openMovieDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            hint = "Search..",
            viewModel.searchQuery.value
        ) {
            viewModel.searchQuery.value = it
        }

        SearchList {
            openMovieDetail(it.toString())
        }
        MovieTypes()
    }
}

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchList(
    viewModel: SearchViewModel = hiltViewModel(),
    openMovieDetail: (Int) -> Unit,
) {
    when (val searchState = viewModel.searchFlow.collectAsState(initial = Resource.Empty).value) {
        is Resource.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 70.dp)
            ) {
                items(searchState.data.movies) { item ->
                    SearchItem(item) {
                        openMovieDetail(it)
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
        is Resource.Loading -> {
            CircularProgressIndicator()
        }
        else -> {

        }
    }
}

@Composable
fun SearchItem(
    item: Movie,
    itemClicked: (Int) -> Unit
) {
    val painter =
        rememberCoilPainter(
            request = item.poster?.original,
            fadeIn = true
        )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                itemClicked(item.movieId)
            }
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.releaseDate ?: "",
                color = Color.Gray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize()
            )
            Spacer(modifier = Modifier.height(12.dp))
            CircularProgress(percentage = 0.7f, number = 100)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieTypes() {
    Text(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
        text = "Genres",
        style = Typography.h6
    )
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 60.dp)
    ) {

        items(listOf(Color.LightGray, 2, 3, 4, 5, 6, 7, 8)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(60.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                Text(
                    text = "Horror", color = Color.White,
                    style = Typography.subtitle1
                )
            }
        }
    }
}