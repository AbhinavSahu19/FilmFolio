package com.example.moviez.presentation.seasonDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.presentation.commons.formatDateString
import com.example.moviez.presentation.commons.formatTime
import com.example.moviez.presentation.commons.toSingleDecimal

@Composable
fun SeasonSideDetails(
    seasonNumb: Int,
    rating: Double,
    releaseDate: String?,
    episodes: Int ,
    runtime: Int
){
    Column(
        Modifier
            .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 7.dp),){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Season :-  ",
                    fontSize = 16.sp,
                )
                Text(
                    text = seasonNumb.toString(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
        ) {
            if(episodes >= 10){
                Column(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Episodes :-  ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = episodes.toString(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
            else{
                Row(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Episodes :-  ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = episodes.toString(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Rating ",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = if(rating!=0.0) toSingleDecimal(rating) + "/10" else "--",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Runtime",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = formatTime(runtime),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Release On",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = formatDateString(releaseDate),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}