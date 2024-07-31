package com.example.moviez.presentation.seriesDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviez.BuildConfig
import com.example.moviez.R
import com.example.moviez.dataModels.SeriesSeasonListItem
import com.example.moviez.presentation.commons.formatDateString
import com.example.moviez.presentation.commons.toSingleDecimal

@Composable
fun SeasonCard(
    season: SeriesSeasonListItem,
    onSeasonClick: (Int) -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        border = BorderStroke(1.dp, colorResource(id = R.color.bg_gray)),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(7.dp, 10.dp)
            .clickable { onSeasonClick(season.seasonNumber) }
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            SeasonItemImageCard(posterPath = season.posterPath)
            Text(text = "Season ${season.seasonNumber} - ${season.episodeCount} Episodes",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.unselected_gray),
                modifier = Modifier.padding(10.dp, 2.dp,10.dp, 0.dp))
            Text(text = season.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.text),
                modifier = Modifier.padding(10.dp, 3.dp,15.dp, 0.dp))
            Text(
                text = if(season.voteAverage != 0.0)toSingleDecimal(season.voteAverage) + "/10" else "--",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.text),
                modifier = Modifier.padding(10.dp, 2.dp,10.dp, 0.dp))
            Text(text = "Release On:- ${formatDateString(season.airDate)}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.text),
                modifier = Modifier.padding(10.dp, 2.dp,10.dp, 0.dp))
        }
    }
}



@Composable
@Preview
fun SeasonCardPreview(){
    SeasonCard(
        SeriesSeasonListItem(
            "1999-12-12",
            6,
            234,
            "Season 1",
            "Trouble is brewing in the Seven Kingdoms of Westeros. For the driven inhabitants of this visionary world, control of Westeros' Iron Throne holds the lure of great power. But in a land where the seasons can last a lifetime, winter is coming...and beyond the Great Wall that protects them, an ancient evil has returned. In Season One, the story centers on three primary areas: the Stark and the Lannister families, whose designs on controlling the throne threaten a tenuous peace; the dragon princess Daenerys, heir to the former dynasty, who waits just over the Narrow Sea with her malevolent brother Viserys; and the Great Wall--a massive barrier of ice where a forgotten danger is stirring.",
            "342",
            22,
            2.65
        ),
        onSeasonClick = {}
    )
}