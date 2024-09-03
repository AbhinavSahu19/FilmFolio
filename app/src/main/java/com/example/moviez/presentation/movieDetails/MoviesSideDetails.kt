package com.example.moviez.presentation.movieDetails

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.moviez.presentation.commons.formatMoney
import com.example.moviez.presentation.commons.formatTime
import com.example.moviez.presentation.commons.toSingleDecimal

//@RequiresApi(Build.VERSION_CODES.O)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoviesSideDetails(
    adult: Boolean,
    popularity: Double,
    rating: Double,
    voteCount: Int,
    runtime: Int,
    status: String,
    releaseDate: String,
    budget: Long,
    revenue: Long
){
    Column(
        Modifier
            .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 7.dp),
        ){
//        Card(
//            colors = CardDefaults.cardColors(
//                containerColor = colorResource(id = R.color.white)
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 5.dp)
//        ) {
//            Text(text = "Popularity",
//                fontSize = 15.sp,
//                modifier = Modifier.padding(start = 10.dp, top = 5.dp))
//            Text(text = popularity.toString(),
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium,
//                modifier = Modifier.padding(start = 10.dp))
//        }
        MovieSideRating(rating = rating)
        MovieSideRuntime(runtime = runtime)
        MovieSideStatus(status = status)
        MovieSideReleaseDate(releaseDate = releaseDate)
        MovieSideRevenueBudget(
            revenue = revenue,
            budget = budget
        )
        MovieSideAdult(
            adult = adult
        )
    }
}

@Composable
fun MovieSideAdult(adult: Boolean) {
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
fun MovieSideRevenueBudget(revenue: Long, budget: Long) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ){
        val size = if(formatMoney(revenue).length>9)4.dp else 10.dp
        Column (
            modifier = Modifier.padding(size, 6.dp)
        ){
            Text(text = "Budget",
                fontSize = 16.sp,
            )
            Text(text =  if(budget == 0L) "--" else "$${formatMoney(budget)}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(2.dp))
            HorizontalDivider()
            Text(text = "Revenue",
                fontSize = 16.sp,
            )
            Text(text = if(revenue == 0L) "--" else "$${formatMoney(revenue)}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun MovieSideReleaseDate(releaseDate: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ){
        Column(
            modifier = Modifier.padding(10.dp, 5.dp)

        ) {
            Text(
                text = "Release Date",
                fontSize = 16.sp,
            )
            Text(
                text = formatDateString(releaseDate),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun MovieSideStatus(status: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ){
        Column(
            modifier = Modifier.padding(10.dp, 5.dp)

        ) {
            Text(
                text = "Status",
                fontSize = 16.sp,
            )
            Text(
                text = status,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun MovieSideRuntime(runtime: Int) {
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
                text = "Runtime",
                fontSize = 16.sp,
            )
            Text(
                text = formatTime(runtime),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }

    }
}

@Composable
fun MovieSideRating(
    rating: Double
){
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
                text = "Ratings",
                fontSize = 16.sp,
            )
            Text(
                text = if(rating != 0.0)toSingleDecimal(rating) + "/10" else "--",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
//                Text(
//                    text = voteCount.toString(),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium,
//                )
        }
    }
}