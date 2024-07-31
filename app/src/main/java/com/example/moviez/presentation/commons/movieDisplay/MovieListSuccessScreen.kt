package com.example.moviez.presentation.commons.movieDisplay

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieListSuccessScreen(
    movieResponse: MovieListResponse,
    onPageChange: (Int) -> Unit,
    onMovieClick: (Int) -> Unit
){
    Scaffold (
        topBar = {
            GeneralTopBar()
        },
        bottomBar =
        { GeneralBottomBar() }

    ){
        MovieListDisplay(movieResponse = movieResponse,
            onPageChange = onPageChange,
            onMovieClick = onMovieClick)

    }
}


