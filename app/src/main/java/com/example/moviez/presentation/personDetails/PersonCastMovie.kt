package com.example.moviez.presentation.personDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.moviez.dataModels.MovieListItem
import com.example.moviez.presentation.commons.itemCards.MidItemCard

@Composable
fun PersonCastMovie(
    movieList: List<MovieListItem>,
    onMovieClick: (Int)-> Unit
){

    if(movieList.isNotEmpty()){
        Card (
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 0.dp, 7.dp, 10.dp)
        ){
            Column {
                Text(text = "As Cast in Movies",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(15.dp, 5.dp,)
                )
                LazyRow (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, bottom = 6.dp)
                ){
                    items(movieList){item->
                        Card (
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.white)
                            ),
                            border = BorderStroke(0.dp, colorResource(id = R.color.bg_gray)),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .padding(start = 5.dp, end = 3.dp)
                        ) {
                            MidItemCard(
                                posterPath = item.posterPath,
                                onItemClick = {
                                    onMovieClick(item.id)
                                },
                                itemId = item.id,
                                itemType = "m"
                            )
                        }
                    }
                }
            }
        }
    }
}