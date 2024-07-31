package com.example.moviez.presentation.searchResults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviez.dataModels.SeriesListItem
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.presentation.commons.seriesDisplay.SeriesListDisplay

@Composable
fun SearchResultSeriesList(
    seriesResponse: SeriesListResponse,
    onSeriesClick: (Int) -> Unit,
    onPageChange: (Int) -> Unit
){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (seriesResponse.results.isEmpty()) {
            NothingFoundScreen()
        } else {
            SeriesListDisplay(seriesResponse = seriesResponse,
                onPageChange = onPageChange,
                onSeriesClick = onSeriesClick
            )
        }
    }
}

@Composable
@Preview
fun SearchResultSeriesListPreview(){
    SearchResultSeriesList(
        onSeriesClick = {},
        onPageChange = {},
        seriesResponse = SeriesListResponse(
            pageNo = 1,
            totalPages = 1,
            results = listOf(
                SeriesListItem(),
                SeriesListItem(),
                SeriesListItem(),
                SeriesListItem(),
                SeriesListItem(),
                SeriesListItem(),)
        )
    )
}