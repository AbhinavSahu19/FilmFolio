
package com.example.moviez.presentation.seasonDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.SeriesForSeasons
import com.example.moviez.dataModels.SeriesSeasonDetails
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object SeriesSeasonDestination: NavigationDestination{
    override val route: String = "series_season"
    const val seriesIdArgs = "season_id"
    const val seasonNumbArgs = "season_number"
    val routeWithArgs = "$route/{$seriesIdArgs}/{$seasonNumbArgs}"
}

@HiltViewModel
class SeriesSeasonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel() {
    val seriesId: Int = checkNotNull(savedStateHandle[SeriesSeasonDestination.seriesIdArgs])
    val seasonNumb: Int = checkNotNull(savedStateHandle[SeriesSeasonDestination.seasonNumbArgs])

    private val _seasonDetail = MutableStateFlow<ResponseModel<SeriesSeasonDetails>>(ResponseModel.Loading)
    val seasonDetails: StateFlow<ResponseModel<SeriesSeasonDetails>> = _seasonDetail

    private val _seasonNumbers = MutableStateFlow<ResponseModel<SeriesForSeasons>>(ResponseModel.Loading)
    val seasonNumbers: StateFlow<ResponseModel<SeriesForSeasons>> = _seasonNumbers

    init {
        load()
    }
    fun load(){
        getSeasonDetails(seasonNumb)
        getSeasonNumbers()
    }

    fun getSeasonDetails(seasonNumb: Int){
        viewModelScope.launch {
            repo.getSeriesSeasonDetails(seriesId, seasonNumb).collect{
                _seasonDetail.value = it
            }
        }
    }

    fun getSeasonNumbers(){
        viewModelScope.launch {
            repo.getSeriesSeasonNumbers(seriesId, ).collect{
                _seasonNumbers.value = it
            }
        }
    }
}