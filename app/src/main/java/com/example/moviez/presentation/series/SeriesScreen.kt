package com.example.moviez.presentation.series

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.HalfLoadingScreen
import com.example.moviez.utils.ResponseModel

@Composable
fun SeriesScreen(
    seriesViewModel: SeriesViewModel = hiltViewModel(),
    navigateToSeriesListWithNumb: (Int) -> Unit,
    navigateToSeriesDetails: (Int) -> Unit
){
    val trendingSeries by seriesViewModel.trendingSeries.collectAsState()
    val popularSeries by seriesViewModel.popularSeries.collectAsState()
    val topRatedSeries by seriesViewModel.topRatedSeries.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(trendingSeries is ResponseModel.Error){
            ErrorScreen(
                errorMsg = (trendingSeries as ResponseModel.Error).errorMsg,
                onReload = seriesViewModel::getAll
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.bg_gray))
                    .scrollable(rememberScrollState(), Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    when (trendingSeries) {
                        is ResponseModel.Error -> {
                            ErrorScreen(
                                errorMsg = (trendingSeries as ResponseModel.Error).errorMsg,
                                onReload = seriesViewModel::getAll
                            )
                        }

                        ResponseModel.Loading -> {
                            HalfLoadingScreen()
                        }

                        is ResponseModel.Success -> {
                            SeriesTypeDisplay(
                                type = "Trending Shows",
                                typeNumb = 0,
                                seriesList = (trendingSeries as ResponseModel.Success).data.results,
                                onArrowClick = navigateToSeriesListWithNumb,
                                onSeriesClick = navigateToSeriesDetails
                            )
                        }
                    }
                }
                item {
                    when (popularSeries) {
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> {
                            HalfLoadingScreen()
                        }

                        is ResponseModel.Success -> {
                            SeriesTypeDisplay(
                                type = "Popular Shows",
                                typeNumb = 1,
                                seriesList = (popularSeries as ResponseModel.Success).data.results,
                                onArrowClick = navigateToSeriesListWithNumb,
                                onSeriesClick = navigateToSeriesDetails
                            )
                        }
                    }
                }
                item {
                    when (topRatedSeries) {
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> {
                            HalfLoadingScreen()
                        }

                        is ResponseModel.Success -> {
                            SeriesTypeDisplay(
                                type = "Top Rated Shows",
                                typeNumb = 2,
                                seriesList = (topRatedSeries as ResponseModel.Success).data.results,
                                onArrowClick = navigateToSeriesListWithNumb,
                                onSeriesClick = navigateToSeriesDetails
                            )
                        }
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(70.dp)
                            .fillParentMaxWidth()
                    )
                }
            }
        }
    }
}

