package com.example.moviez.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.HalfLoadingScreen
import com.example.moviez.utils.ResponseModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    movieScreenViewModel: MoviesViewModel = hiltViewModel(),
    navigateToMovieListWithNumb: (Int) -> Unit,
    navigateToMovieDetails: (Int) -> Unit
){
    val trendingMovies by movieScreenViewModel.trendingMovies.collectAsState()
    val popularMovies by movieScreenViewModel.popularMovies.collectAsState()
    val nowPlayingMovies by movieScreenViewModel.nowPlayingMovies.collectAsState()
    val topRatedMovies by movieScreenViewModel.topRatedMovies.collectAsState()
    val upcomingMovies by movieScreenViewModel.upcomingMovies.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(trendingMovies is ResponseModel.Error){
            ErrorScreen(
                errorMsg = (trendingMovies as ResponseModel.Error).errorMsg,
                onReload = movieScreenViewModel::getAll
            )
        }
        else{
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(colorResource(id = R.color.bg_gray))
                    .scrollable(rememberScrollState(), Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    when(trendingMovies){
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> { HalfLoadingScreen() }
                        is ResponseModel.Success -> {
                            MovieTypeDisplay(
                                type = "Trending Movies",
                                movieList = (trendingMovies as ResponseModel.Success).data.results,
                                typeNumb = 0,
                                onArrowClick = navigateToMovieListWithNumb,
                                onMovieClick = navigateToMovieDetails)
                        }
                    }
                }
                item{
                    when(popularMovies){
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> { HalfLoadingScreen() }
                        is ResponseModel.Success -> {
                            MovieTypeDisplay(type = "Popular Movies",
                                movieList = (popularMovies as ResponseModel.Success).data.results,
                                typeNumb = 1,
                                onArrowClick = navigateToMovieListWithNumb,
                                onMovieClick = navigateToMovieDetails)
                        }
                    }
                }
                item{
                    when(nowPlayingMovies){
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> { HalfLoadingScreen() }
                        is ResponseModel.Success -> {
                            MovieTypeDisplay(type = "Now Playing Movies",
                                movieList = (nowPlayingMovies as ResponseModel.Success).data.results,
                                typeNumb = 2,
                                onArrowClick = navigateToMovieListWithNumb,
                                onMovieClick = navigateToMovieDetails)
                        }
                    }
                }
                item{
                    when(topRatedMovies){
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> { HalfLoadingScreen() }
                        is ResponseModel.Success -> {
                            MovieTypeDisplay(type = "Top Rated Movies",
                                movieList = (topRatedMovies as ResponseModel.Success).data.results,
                                typeNumb = 3,
                                onArrowClick = navigateToMovieListWithNumb,
                                onMovieClick = navigateToMovieDetails)
                        }
                    }
                }
                item {
                    when(upcomingMovies){
                        is ResponseModel.Error -> {}
                        ResponseModel.Loading -> { HalfLoadingScreen() }
                        is ResponseModel.Success -> {
                            MovieTypeDisplay(type = "Upcoming Movies",
                                movieList = (upcomingMovies as ResponseModel.Success).data.results,
                                typeNumb = 4,
                                onArrowClick = navigateToMovieListWithNumb,
                                onMovieClick = navigateToMovieDetails
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier
                        .height(70.dp)
                        .fillParentMaxWidth())
                }
            }
        }
    }
}
