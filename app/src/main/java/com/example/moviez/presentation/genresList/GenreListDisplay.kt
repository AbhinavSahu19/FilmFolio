package com.example.moviez.presentation.genresList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.dataModels.GenreListItem

@Composable
fun GenreListDisplayScreen(
    genreList: List<GenreListItem>,
    navigateToNext: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .background(color = colorResource(id = R.color.bg_gray))
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(genreList) { genre ->
            Card(
                modifier = Modifier
                    .clickable {
                        navigateToNext(genre.genreId)
                    }
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 2.dp, bottom = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                ),
            ) {
                Text(
                    text = genre.genreName,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(15.dp, 10.dp)
                        .fillMaxWidth(),
                    color = Color.Black
                )
            }
        }
    }
}


@Composable
@Preview
fun GenreListPreview() {
    GenreListDisplayScreen(genreList = listOf(
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
        GenreListItem(0, "Action"),
    ),
        navigateToNext = {})
}