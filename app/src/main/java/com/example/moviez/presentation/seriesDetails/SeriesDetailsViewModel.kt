package com.example.moviez.presentation.seriesDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.SeriesDetails
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

object SeriesDetailsDestination: NavigationDestination {
    override val route = "series_details"
    const val seriesIdArgs = "seriesId"
    val routeWithArgs = "$route/{$seriesIdArgs}"
}

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel(){
    private val seriesId: Int = checkNotNull(savedStateHandle[SeriesDetailsDestination.seriesIdArgs])

    private val _seriesDetails = MutableStateFlow<ResponseModel<SeriesDetails>>(ResponseModel.Loading)
    val seriesDetails: StateFlow<ResponseModel<SeriesDetails>> = _seriesDetails

    init {
        getSeriesDetails()
    }

     fun getSeriesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSeriesDetails(seriesId).collect {
                withContext(Dispatchers.Main){
                _seriesDetails.value = it}
            }
        }
    }
}