package com.example.moviez.presentation.commons.seriesDisplay

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
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.presentation.commons.itemCards.ItemDisplayCard
import com.example.moviez.presentation.commons.ListPageChangeBar

@Composable
fun SeriesListDisplay(
    seriesResponse: SeriesListResponse,
    onPageChange: (Int) -> Unit,
    onSeriesClick: (Int) -> Unit
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
            items(seriesResponse.results.chunked(2)){pair->
                Row (
                ){
                    pair.forEach{series->
                        ItemDisplayCard(posterPath = series.posterPath,
                            onItemClick = {onSeriesClick(series.id)},
                            itemId = series.id,
                            itemType = "s"
                        )
                    }
                }
            }
        }
        ListPageChangeBar(currentPage = seriesResponse.pageNo,
            totalPage = seriesResponse.totalPages,
            onPageChange = { onPageChange(it) }
        )
    }
}