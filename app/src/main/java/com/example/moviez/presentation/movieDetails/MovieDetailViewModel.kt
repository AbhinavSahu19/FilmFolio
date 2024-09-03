package com.example.moviez.presentation.movieDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.MovieDetails
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.network.DataCache
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

object MovieDetailsDestination: NavigationDestination {
    override val route = "movie_details"
    const val movieIdArgs = "movieId"
    val routeWithArgs = "$route/{$movieIdArgs}"
}

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository,
): ViewModel(){
    val movieId : Int = checkNotNull(savedStateHandle[MovieDetailsDestination.movieIdArgs])

    private val _movieDetails = MutableStateFlow<ResponseModel<MovieDetails>>(ResponseModel.Loading)
    val movieDetails: StateFlow<ResponseModel<MovieDetails>> = _movieDetails

    init {
        getMovieDetails()
    }

    fun getMovieDetails() {
        viewModelScope.launch{
            repo.getMovieDetails(movieId).collect {
               _movieDetails.value = it
            }
        }
    }
}