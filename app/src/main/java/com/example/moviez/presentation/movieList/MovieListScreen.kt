package com.example.moviez.presentation.movieList

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
import com.example.moviez.presentation.commons.movieDisplay.MovieListSuccessScreen
import com.example.moviez.utils.ResponseModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieListScreen(
    movieListViewModel: MovieListViewModel = hiltViewModel(),
    navigateToMovieDetails: (Int) -> Unit
) {
    val movieListResponse by movieListViewModel.movieListResponse.collectAsState()

    var currentPage by remember {
        mutableIntStateOf(1)
    }

    when (movieListResponse) {
        is ResponseModel.Error -> {
            ErrorScreen(
                errorMsg = (movieListResponse as ResponseModel.Error).errorMsg,
                onReload = { movieListViewModel.getMovies(currentPage) }
            )
        }

        ResponseModel.Loading -> {
            LoadingScreen()
        }

        is ResponseModel.Success ->
            MovieListSuccessScreen(
                movieResponse = (movieListResponse as ResponseModel.Success).data,
                onPageChange = {
                    movieListViewModel.getMovies(it)
                    currentPage = it
                },
                onMovieClick = navigateToMovieDetails
            )
    }
}

