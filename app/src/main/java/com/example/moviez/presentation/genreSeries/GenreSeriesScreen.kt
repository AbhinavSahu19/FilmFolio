package com.example.moviez.presentation.genreSeries

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
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
fun GenreSeriesScreen(
    genreSeriesViewModel: GenreSeriesViewModel = hiltViewModel(),
    navigateToSeriesDetails: (Int) -> Unit
){
    val seriesListResponse by genreSeriesViewModel.seriesWithGenreResponse.collectAsState()
//    val seriesList = remember {
//        mutableStateListOf<TvListItem>()
//    }
    var currentPage by remember {
        mutableIntStateOf(1)
    }
    Scaffold {
        when(seriesListResponse){
            is ResponseModel.Error -> {
                ErrorScreen(
                    errorMsg = (seriesListResponse as ResponseModel.Error).errorMsg,
                    onReload = { genreSeriesViewModel.getSeriesWithGenre(currentPage) }
                )
            }
            ResponseModel.Loading -> {
                LoadingScreen()
            }
            is ResponseModel.Success -> {
//                seriesList.clear()
//                seriesList.addAll((seriesListResponse as ResponseModel.Success).data.results)

                SeriesListSuccessScreen(seriesResponse = (seriesListResponse as ResponseModel.Success).data,
                    onPageChange = {
                        genreSeriesViewModel.getSeriesWithGenre(it)
                        currentPage= it
                    },
                    onSeriesClick = {
                        navigateToSeriesDetails(it)
                    })
            }
        }
    }
}

