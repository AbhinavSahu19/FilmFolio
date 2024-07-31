package com.example.moviez.presentation.searchResults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.presentation.commons.movieDisplay.MovieListDisplay

@Composable
fun SearchResultMovieList(
    movieResponse: MovieListResponse,
    onMovieClick: (Int) -> Unit,
    onPageChange: (Int) -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (movieResponse.results.isEmpty()) {
                NothingFoundScreen()
            } else {
                MovieListDisplay(
                    movieResponse = movieResponse,
                    onPageChange = onPageChange,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}