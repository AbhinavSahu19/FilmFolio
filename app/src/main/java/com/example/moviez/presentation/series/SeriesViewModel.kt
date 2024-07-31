package com.example.moviez.presentation.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.SeriesListResponse
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

object SeriesDestination: NavigationDestination {
    override val route = "series"
}

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repo: TmdbRepository
): ViewModel(){
    private val _trendingSeries = MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val trendingSeries : StateFlow<ResponseModel<SeriesListResponse>> = _trendingSeries

    private val _popularSeries = MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val popularSeries : StateFlow<ResponseModel<SeriesListResponse>> = _popularSeries

    private val _topRatedSeries = MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val topRatedSeries : StateFlow<ResponseModel<SeriesListResponse>> = _topRatedSeries

    init {
        getTrendingSeries()
        getPopularSeries()
        getTopRatedSeries()
    }
    fun getAll(){
        getTrendingSeries()
        getPopularSeries()
        getTopRatedSeries()
    }
    fun getTrendingSeries(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTrendingSeries(1).collect{
                withContext(Dispatchers.Main){ _trendingSeries.value = it }
            }
        }
    }

    fun getPopularSeries(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getPopularSeries(1).collect{
                withContext(Dispatchers.Main){ _popularSeries.value = it }
            }
        }
    }

    fun getTopRatedSeries(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTopRatedSeries(1).collect{
                withContext(Dispatchers.Main){ _topRatedSeries.value = it }
            }
        }
    }
}