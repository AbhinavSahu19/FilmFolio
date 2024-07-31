package com.example.moviez.presentation.genresList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviez.presentation.genreMovies.GenreMoviesDestination
import com.example.moviez.presentation.genreSeries.GenreSeriesDestination
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.utils.ResponseModel

@Composable
fun GenreListScreen(
    genreListViewModel: GenreListViewModel = hiltViewModel(),
    navigateToMovieListWithGenre: (Int) -> Unit,
    navigateToSeriesListWithGenre: (Int) -> Unit,
) {
    val movieGenreListResponse by genreListViewModel.movieGenreList.collectAsState()
    val seriesGenreListResponse by genreListViewModel.tvGenreList.collectAsState()

    val option = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GenreOptionBar(
            option = option.value,
            onChange = {
                option.value = it
                if(it)genreListViewModel.getTvGenreList()
            }
        )

        if (option.value) {
            when(seriesGenreListResponse) {
                is ResponseModel.Error -> {
                    ErrorScreen(
                        (seriesGenreListResponse as ResponseModel.Error).errorMsg,
                        onReload = genreListViewModel::getTvGenreList
                    )
                }

                ResponseModel.Loading -> {
                    LoadingScreen()
                }

                is ResponseModel.Success -> {
                    GenreListDisplayScreen(genreList = (seriesGenreListResponse as ResponseModel.Success).data.genres,
                        navigateToNext = navigateToSeriesListWithGenre)
                }
            }
        } else {
            when (movieGenreListResponse) {
                is ResponseModel.Error -> {
                    ErrorScreen(
                        (movieGenreListResponse as ResponseModel.Error).errorMsg,
                        onReload = genreListViewModel::getMovieGenreList
                    )
                }

                ResponseModel.Loading -> {
                    LoadingScreen()
                }

                is ResponseModel.Success -> {
                    GenreListDisplayScreen(genreList = (movieGenreListResponse as ResponseModel.Success).data.genres,
                        navigateToNext = navigateToMovieListWithGenre)
                }
            }
        }
    }
}
