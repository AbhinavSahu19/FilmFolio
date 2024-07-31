package com.example.moviez.presentation.seriesList

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.utils.ResponseModel
import com.example.moviez.presentation.commons.seriesDisplay.SeriesListSuccessScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeriesListScreen(
    seriesListViewModel: SeriesListViewModel = hiltViewModel(),
    navigateToSeriesDetails: (Int) -> Unit,
){
    val seriesListResponse by seriesListViewModel.seriesListResponse.collectAsState()
    var currentPage by remember {
        mutableIntStateOf(1)
    }
    when(seriesListResponse){
        is ResponseModel.Error -> {
            ErrorScreen(errorMsg = (seriesListResponse as ResponseModel.Error).errorMsg,
                onReload = { seriesListViewModel.getSeriesList(currentPage) }
            )
        }
        ResponseModel.Loading -> {
            LoadingScreen()
        }
        is ResponseModel.Success -> {
            SeriesListSuccessScreen(seriesResponse = (seriesListResponse as ResponseModel.Success).data,
                onPageChange = {
                    seriesListViewModel.getSeriesList(it)
                    currentPage = it
                },
                onSeriesClick = {
                    navigateToSeriesDetails(it)
                }
            )
        }
    }
}