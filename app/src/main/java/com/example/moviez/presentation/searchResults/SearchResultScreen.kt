package com.example.moviez.presentation.searchResults

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.utils.ResponseModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchResultScreen(
    searchResultViewModel: SearchResultViewModel = hiltViewModel(),
    navigateToSearch: (String) -> Unit,
    navigateToHome: () -> Unit,
    navigateToMovieDetails: (Int) -> Unit,
    navigateToSeriesDetails: (Int) -> Unit,
    navigateToPersonDetails: (Int) -> Unit,
){
    BackHandler {
        navigateToHome()
    }
    val query = remember {
        mutableStateOf(searchResultViewModel.searchQuery)
    }
    val movieResultResponse by searchResultViewModel.movieResultResponse.collectAsState()
    val seriesResultResponse by searchResultViewModel.seriesResultResponse.collectAsState()
    val personResultResponse by searchResultViewModel.personResultResponse.collectAsState()
    val option = remember {
        mutableIntStateOf(0)
    }
    val moviePageNo = remember {
        mutableIntStateOf(1)
    }
    val seriesPageNo = remember {
        mutableIntStateOf(1)
    }
    val personPageNo = remember {
        mutableIntStateOf(1)
    }
    LaunchedEffect(option.intValue, moviePageNo.intValue) {
        if (option.intValue == 0) {
            searchResultViewModel.getMovieResultResponse(moviePageNo.intValue)
        }
    }

    LaunchedEffect(option.intValue, seriesPageNo.intValue) {
        if (option.intValue == 1) {
            searchResultViewModel.getSeriesResultResponse(seriesPageNo.intValue)
        }
    }

    LaunchedEffect(option.intValue, personPageNo.intValue) {
        if (option.intValue == 2) {
            searchResultViewModel.getPersonResultResponse(personPageNo.intValue)
        }
    }
    Scaffold(
        topBar = {
            GeneralTopBar()
        },
        bottomBar = {
            GeneralBottomBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.bg_gray))
        ) {
            SearchResultUpperPart(
                query = query.value,
                onQueryClick = { navigateToSearch(query.value) },
                onCrossClick = { navigateToSearch("")},
                option = option.intValue,
                onOptionChange = { option.intValue = it },
            )
            when (option.intValue) {
                1 -> {
                    when (seriesResultResponse) {
                        is ResponseModel.Error -> {
                            ErrorScreen(errorMsg = (seriesResultResponse as ResponseModel.Error).errorMsg,
                                onReload = {
                                    searchResultViewModel.getSeriesResultResponse(
                                        seriesPageNo.intValue
                                    )
                                }
                            )
                        }

                        ResponseModel.Loading -> {
                            LoadingScreen()
                        }

                        is ResponseModel.Success -> {
                            SearchResultSeriesList(seriesResponse = (seriesResultResponse as ResponseModel.Success).data,
                                onSeriesClick = {navigateToSeriesDetails(it)},
                                onPageChange = {
                                    searchResultViewModel.getSeriesResultResponse(it)
                                    seriesPageNo.intValue = it
                                })
                        }
                    }
                }

                2 -> {
                    when (personResultResponse) {
                        is ResponseModel.Error -> {
                            ErrorScreen(errorMsg = (personResultResponse as ResponseModel.Error).errorMsg,
                                onReload = {
                                    searchResultViewModel.getPersonResultResponse(
                                        personPageNo.intValue
                                    )
                                }
                            )
                        }

                        ResponseModel.Loading -> {
                            LoadingScreen()
                        }

                        is ResponseModel.Success -> {
                            SearchResultPersonList(personResponse = (personResultResponse as ResponseModel.Success).data,
                                onPersonClick = {
                                    navigateToPersonDetails(it)
                                },
                                onPageChange = {
                                    searchResultViewModel.getPersonResultResponse(it)
                                    personPageNo.intValue = it
                                })
                        }
                    }
                }

                0 -> {
                    when (movieResultResponse) {
                        is ResponseModel.Error -> {
                            ErrorScreen(errorMsg = (movieResultResponse as ResponseModel.Error).errorMsg,
                                onReload = {
                                    searchResultViewModel.getMovieResultResponse(
                                        moviePageNo.intValue
                                    )
                                }
                            )
                        }

                        ResponseModel.Loading -> {
                            LoadingScreen()
                        }

                        is ResponseModel.Success -> {
                            SearchResultMovieList(
                                movieResponse = (movieResultResponse as ResponseModel.Success).data,
                                onMovieClick = { navigateToMovieDetails(it) },
                                onPageChange = { searchResultViewModel.getMovieResultResponse(it)
                                moviePageNo.intValue = it}
                            )
                        }
                    }
                }
            }
        }
    }
}

