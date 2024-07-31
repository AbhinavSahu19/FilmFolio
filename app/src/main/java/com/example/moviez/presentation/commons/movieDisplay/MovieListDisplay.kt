package com.example.moviez.presentation.commons.movieDisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.moviez.R
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.presentation.commons.itemCards.ItemDisplayCard
import com.example.moviez.presentation.commons.ListPageChangeBar

@Composable
fun MovieListDisplay(
    movieResponse: MovieListResponse,
    onPageChange: (Int) -> Unit,
    onMovieClick: (Int) -> Unit
){
    Column (
        modifier = Modifier
            .padding(bottom = 15.dp)
            .background(colorResource(id = R.color.bg_gray)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(movieResponse.results.chunked(2)){pair->
                Row (
                ){
                    pair.forEach{movie->
                        ItemDisplayCard(posterPath = movie.posterPath,
                            onItemClick = {onMovieClick(movie.id) },
                            itemId = movie.id,
                            itemType = "m")
                    }
                }
            }
        }
        ListPageChangeBar(currentPage = movieResponse.pageNo,
            totalPage = movieResponse.totalPages,
            onPageChange = { onPageChange(it) }
        )
    }
}
