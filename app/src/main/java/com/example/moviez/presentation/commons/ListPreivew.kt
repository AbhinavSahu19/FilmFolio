package com.example.moviez.presentation.commons

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviez.dataModels.MovieListItem
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.PersonListItem
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.dataModels.SeriesListItem
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.presentation.commons.movieDisplay.MovieListSuccessScreen
import com.example.moviez.presentation.commons.personDisplay.PersonListDisplay
import com.example.moviez.presentation.commons.seriesDisplay.SeriesListSuccessScreen

@Composable
@Preview
fun MovieListPreview(){
    MovieListSuccessScreen(
        movieResponse = MovieListResponse(
            3,
            listOf(
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),
                MovieListItem(),

                ),
            3,
            3
        ),
        onMovieClick = {},
        onPageChange = {}
    )
}

@Composable
@Preview
fun SeriesListPreview() {
    SeriesListSuccessScreen(seriesResponse = SeriesListResponse(
        pageNo = 1,
        results = listOf(
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
        ),
        totalPages = 3,
        totalResults = 3
    ),
        onPageChange = {},
        onSeriesClick = {}
    )
}

@Composable
@Preview
fun PersonListPreview() {
    PersonListDisplay(personResponse =
    PersonListResponse(
        pageNo = 1,
        results = listOf(
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
            PersonListItem(),
        ),
        totalPages = 3,
        totalResults = 3
    ),
        onPageChange = {},
        onPersonClick = {})
}