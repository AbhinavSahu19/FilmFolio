package com.example.moviez.presentation.commons.seriesDisplay

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesListSuccessScreen(
    seriesResponse: SeriesListResponse,
    onPageChange: (Int) -> Unit,
    onSeriesClick: (Int) -> Unit
){

    Scaffold (
        topBar = {
            GeneralTopBar()
        },
        bottomBar = {
            GeneralBottomBar()
        }
    ){
        SeriesListDisplay(
            seriesResponse,
            onPageChange,
            onSeriesClick
        )
    }
}

