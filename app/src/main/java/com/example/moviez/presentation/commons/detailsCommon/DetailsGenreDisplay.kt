package com.example.moviez.presentation.commons.detailsCommon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.dataModels.GenreListItem

@Composable
fun DetailsGenreDisplay(
    genres: List<GenreListItem>,
    onGenreClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 2.dp)
    ) {
        items(genres.size) { idx ->
            val txt = genres[idx].genreName + if(idx != genres.size-1)"," else ""
            Text(
                text = txt,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 0.dp, top = 2.dp, bottom = 2.dp, end = 5.dp)
                    .clickable { onGenreClick(genres[idx].genreId) }
            )

        }
    }
}