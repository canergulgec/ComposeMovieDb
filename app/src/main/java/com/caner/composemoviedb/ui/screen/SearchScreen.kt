package com.caner.composemoviedb.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.Movie
import com.caner.composemoviedb.presentation.SearchViewModel
import com.caner.composemoviedb.ui.component.CircularProgress
import com.caner.composemoviedb.ui.component.SearchBar
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    // Creates a CoroutineScope bound to the MoviesScreen's lifecycle
    val scope = rememberCoroutineScope()

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
            hint = "Search.."
        ) {
            if (it.length > 2) {
                scope.launch {
                    viewModel.searchQuery.emit(it)
                }
            }
        }
        SearchList()
    }
}

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun SearchList(viewModel: SearchViewModel = hiltViewModel()) {
    val a = viewModel.searchFlow.collectAsState(initial = Resource.Empty).value
    when (a) {
        is Resource.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {
                items(a.data.movies) { item ->
                    // Set Search Item
                    SearchItem(item)
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
fun SearchItem(item: Movie) {
    val context = LocalContext.current
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
                Toast
                    .makeText(context, "caner", Toast.LENGTH_SHORT)
                    .show()
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