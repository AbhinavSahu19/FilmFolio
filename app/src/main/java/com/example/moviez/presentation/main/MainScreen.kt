package com.example.moviez.presentation.main

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.commons.storedListDisplay.StoredListDestination
import com.example.moviez.presentation.genreMovies.GenreMoviesDestination
import com.example.moviez.presentation.genreSeries.GenreSeriesDestination
import com.example.moviez.presentation.genresList.GenreListScreen
import com.example.moviez.presentation.home.HomeScreen
import com.example.moviez.presentation.movieDetails.MovieDetailsDestination
import com.example.moviez.presentation.movieList.MovieListDestination
import com.example.moviez.presentation.movies.MovieScreen
import com.example.moviez.presentation.search.SearchDestination
import com.example.moviez.presentation.series.SeriesScreen
import com.example.moviez.presentation.seriesDetails.SeriesDetailsDestination
import com.example.moviez.presentation.seriesList.SeriesListDestination
import com.example.moviez.presentation.settings.SettingScreen
import com.example.moviez.presentation.signIn.SignInDestination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
) {
    val currentScreen by mainViewModel.screen.collectAsState()
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                GeneralTopBar()
                MainSearchBar(
                        navigateToSearch = {
                            val encodedQuery = Uri.encode("")
                            navController.navigate("${SearchDestination.route}?${SearchDestination.queryArgs}=$encodedQuery") }

                )
            }
        },
        bottomBar = {
            BottomBar(currentScreen,
            onclick = {
                mainViewModel.onClick(it)
            }) }
    ) {
        when (currentScreen) {
            Screen.Home -> {
                HomeScreen(navigateToMovieDetails ={ navController.navigate("${MovieDetailsDestination.route}/$it")},
                    navigateTosSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it")},
                    navigateToStoredList = {navController.navigate("${StoredListDestination.route}/$it")},
                    navigateToSignIn = { navController.navigate(SignInDestination.route)}
                )
            }
            Screen.Genres -> {
                GenreListScreen(
                    navigateToSeriesListWithGenre = { navController.navigate("${GenreSeriesDestination.route}/$it")} ,
                    navigateToMovieListWithGenre = { navController.navigate("${GenreMoviesDestination.route}/$it") },
                    )
            }
            Screen.Movies -> {
                MovieScreen(
                    navigateToMovieListWithNumb = { navController.navigate("${MovieListDestination.route}/$it")},
                    navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it")},
                    )
            }
            Screen.Series -> {
                SeriesScreen(
                    navigateToSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it")},
                    navigateToSeriesListWithNumb = { navController.navigate("${SeriesListDestination.route}/$it")},
                )

            }
            Screen.Settings -> {
                SettingScreen(
                    navigateToSignIn = { navController.navigate(SignInDestination.route)}
                )
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview(){
    MainScreen(
        navController = rememberNavController(),
    )
}