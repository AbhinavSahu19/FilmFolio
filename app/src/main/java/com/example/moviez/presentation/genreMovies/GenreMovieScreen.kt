package com.example.moviez.presentation.genreMovies

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
import com.example.moviez.presentation.commons.movieDisplay.MovieListSuccessScreen
import com.example.moviez.utils.ResponseModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenreMoviesScreen(
    genreMoviesViewModel: GenreMoviesViewModel = hiltViewModel(),
    navigateToMovieDetails: (Int) -> Unit
){
    val movieListResponse by genreMoviesViewModel.moviesWithGenreResponse.collectAsState()
    var currentPage by remember {
        mutableIntStateOf(1)
    }
    Scaffold {
        when(movieListResponse){
            is ResponseModel.Error -> {
                ErrorScreen(
                    errorMsg = (movieListResponse as ResponseModel.Error).errorMsg,
                    onReload = { genreMoviesViewModel.getMoviesWithGenre(currentPage) }
                )
            }
            ResponseModel.Loading -> {
                LoadingScreen()
            }
            is ResponseModel.Success -> {
                MovieListSuccessScreen(movieResponse = (movieListResponse as ResponseModel.Success).data,
                    onPageChange = {
                        genreMoviesViewModel.getMoviesWithGenre(it)
                        currentPage = it
                    },
                    onMovieClick = navigateToMovieDetails
                )
            }
        }
    }
}

