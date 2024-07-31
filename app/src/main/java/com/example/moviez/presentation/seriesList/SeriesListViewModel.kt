package com.example.moviez.presentation.seriesList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object SeriesListDestination: NavigationDestination{
    override val route = "series-list"
    const val seriesIdArgs = "list-id"
    val routeWithArgs = "$route/{$seriesIdArgs}"
}

@HiltViewModel
class SeriesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel() {
    val listId: Int = checkNotNull(savedStateHandle[SeriesListDestination.seriesIdArgs])

    private val _seriesListResponse= MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val seriesListResponse : StateFlow<ResponseModel<SeriesListResponse>> = _seriesListResponse

    init {
        getSeriesList(1)
    }
    fun getSeriesList(pageNo: Int){
        when(listId) {
            0 -> {
                viewModelScope.launch {
                    repo.getTrendingSeries(pageNo).collect {
                        _seriesListResponse.value = it
                    }
                }
            }

            1 -> {
                viewModelScope.launch {
                    repo.getPopularSeries(pageNo).collect {
                        _seriesListResponse.value = it
                    }
                }
            }

            2 -> {
                viewModelScope.launch {
                    repo.getTopRatedSeries(pageNo).collect {
                        _seriesListResponse.value = it
                    }
                }
            }
        }
    }
}