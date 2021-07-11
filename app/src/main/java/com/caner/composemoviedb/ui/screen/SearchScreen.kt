package com.caner.composemoviedb.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.caner.composemoviedb.ui.component.CircularProgress
import com.caner.composemoviedb.ui.component.SearchBar
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun SearchScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    )
    {
        Column {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                hint = "Search.."
            ) {
                Log.v("TAG", "message")
            }

            SearchList()
        }
    }
}

@Composable
fun SearchList() {
    val searchList = listOf(1, 2, 3, 4)

    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(searchList.size) {
            // Set Search Item
            SearchItem()
            Divider(
                color = Color.LightGray,
                thickness = 0.5.dp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun SearchItem() {
    val context = LocalContext.current
    val painter =
        rememberCoilPainter(
            request = "https://image.tmdb.org/t/p/w500/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
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
            contentDescription = "MovieName",
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Mortal Kombat",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "2021",
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