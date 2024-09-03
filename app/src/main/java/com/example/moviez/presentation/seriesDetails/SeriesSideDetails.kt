package com.example.moviez.presentation.seriesDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.presentation.commons.formatDateString
import com.example.moviez.presentation.commons.toSingleDecimal

@Composable
fun SeriesSideDetails(
    rating: Double,
    voteCount: Int,
    firstAir: String,
    status:String,
    type: String,
    adult: Boolean,
    episodes: Int,
    seasons: Int,
){
    Column(
        Modifier
            .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 7.dp),
    ) {
        SeriesSideRating(rating = rating)
        SeriesSideFirstAir(firstAir = firstAir)
        SeriesSideStatus(status = status)
        SeriesSideType(type = type)
        SeriesSideSeasonsEpisodes(seasons = seasons, episodes = episodes)
        SeriesSideAdult(adult = adult)
    }
}

@Composable
fun SeriesSideAdult(adult: Boolean) {
    if(adult){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)

            ) {
                Text(text = "Adult",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,)
            }
        }
    }
}

@Composable
fun SeriesSideSeasonsEpisodes(seasons: Int, episodes: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ){
        Column (
            modifier = Modifier.padding(10.dp, 5.dp)
        ){
            Text(text = "Seasons",
                fontSize = 16.sp,
            )
            Text(text =  seasons.toString(),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(2.dp))
            HorizontalDivider()
            Text(text = "Episodes",
                fontSize = 16.sp,
            )
            Text(text = episodes.toString(),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun SeriesSideType(type: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 5.dp)

        ) {
            Text(
                text = "Type",
                fontSize = 16.sp,
            )
            Text(
                text = type,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun SeriesSideStatus(status: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        val pad = if(status=="Returning Series")3.dp else 10.dp
        val txt = if(status=="Returning Series")15.sp else 17.sp
        val txt2 = if(status=="Returning Series")14.sp else 16.sp
        Column(
            modifier = Modifier.padding(pad, 5.dp)

        ) {
            Text(
                text = "Status",
                fontSize = txt2,
            )
            Text(
                text = status,
                fontSize = txt,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}

@Composable
fun SeriesSideFirstAir(firstAir: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 5.dp)

        ) {
            Text(
                text = "First Air On",
                fontSize = 16.sp,
            )
            Text(
                text = formatDateString(firstAir),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun SeriesSideRating(rating: Double) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 5.dp)

        ) {
            Text(
                text = "Rating",
                fontSize = 16.sp,
            )
            Text(
                text = if(rating != 0.0)toSingleDecimal(rating) + "/10" else "--",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }

    }
}
