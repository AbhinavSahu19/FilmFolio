package com.example.moviez.presentation.personDetails

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.seriesDetails.SeriesDetailsDestination
import com.example.moviez.utils.ResponseModel
import com.example.moviez.utils.showToast

@Composable
fun PersonDetailsScreen(
    personDetailsViewModel: PersonDetailsViewModel = hiltViewModel(),
    navigateToSeriesDetails :(Int)-> Unit,
    navigateToMovieDetails:(Int)-> Unit
){
    val personDetailResponse by personDetailsViewModel.personDetails.collectAsState()
    when(personDetailResponse){
        is ResponseModel.Error -> {
            ErrorScreen(errorMsg = (personDetailResponse as ResponseModel.Error).errorMsg) {
                personDetailsViewModel.getPersonDetails()
            }
        }
        ResponseModel.Loading -> {
            LoadingScreen()
        }
        is ResponseModel.Success -> {
            PersonDetailSuccess(personDetails = (personDetailResponse as ResponseModel.Success<PersonDetails>).data,
                navigateToMovieDetails,
                navigateToSeriesDetails)
        }
    }
}