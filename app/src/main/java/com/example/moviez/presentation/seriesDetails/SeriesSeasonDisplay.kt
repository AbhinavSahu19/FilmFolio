package com.example.moviez.presentation.seriesDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.dataModels.SeasonEpisodesListItem
import com.example.moviez.dataModels.SeriesSeasonListItem

@Composable
fun SeriesSeasonDisplay(
    seasons: List<SeriesSeasonListItem>,
    onSeasonClick: (Int)->Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp, 0.dp, 7.dp, 10.dp)
    ) {
        Column {
            Text(text = "Seasons",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(15.dp, 5.dp,15.dp, 0.dp))
            LazyRow {
                items(seasons){season->
                    SeasonCard(
                        season = season,
                        onSeasonClick = {onSeasonClick(season.seasonNumber)}
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun SeriesSeasonDisplayPreview(){
    SeriesSeasonDisplay(
        listOf(
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),
            SeriesSeasonListItem("1999-12-12",8,89, "Season1", "sdjfl as", "asdf", 0, 2.2),

            ),
        onSeasonClick = {}
    )
}