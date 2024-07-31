package com.example.moviez.presentation.searchResults

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.PersonListResponse
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

object SearchResultDestination: NavigationDestination{
    override val route = "search_result"
    const val queryArgs = "query"
    val routeWithArgs = "$route/{$queryArgs}"
}

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel() {
    val searchQuery: String = checkNotNull(savedStateHandle[SearchResultDestination.queryArgs])

    private val _movieResultResponse = MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val movieResultResponse: StateFlow<ResponseModel<MovieListResponse>> = _movieResultResponse

    private val _seriesResultResponse = MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val seriesResultResponse: StateFlow<ResponseModel<SeriesListResponse>> = _seriesResultResponse

    private val _personResultResponse = MutableStateFlow<ResponseModel<PersonListResponse>>(ResponseModel.Loading)
    val personResultResponse: StateFlow<ResponseModel<PersonListResponse>> = _personResultResponse

    init {
        getMovieResultResponse(1)
        getSeriesResultResponse(1)
        getPersonResultResponse(1)
    }

    fun getMovieResultResponse(pageNo: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSearchedMovies(searchQuery, pageNo).collect{
                withContext(Dispatchers.Main) {
                    _movieResultResponse.value = it
                }
            }
        }
    }
    fun getSeriesResultResponse(pageNo: Int){
        viewModelScope.launch (Dispatchers.IO){
            repo.getSearchedSeries(searchQuery, pageNo).collect{
                withContext(Dispatchers.Main) {
                _seriesResultResponse.value = it}
            }
        }
    }
    fun getPersonResultResponse(pageNo: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSearchedPerson(searchQuery, pageNo).collect{
                withContext(Dispatchers.Main){
                _personResultResponse.value = it}
            }
        }
    }
}