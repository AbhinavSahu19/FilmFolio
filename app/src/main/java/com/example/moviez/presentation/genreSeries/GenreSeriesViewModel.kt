package com.example.moviez.presentation.genreSeries

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

object GenreSeriesDestination: NavigationDestination {
    override val route = "genre_series"
    const val genreIdArgs = "genre_id"
    val routeWithArgs = "$route/{$genreIdArgs}"
}
@HiltViewModel
class GenreSeriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel() {
    val genreId: Int = checkNotNull(savedStateHandle[GenreSeriesDestination.genreIdArgs])

    private val _seriesWithGenreResponse = MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val seriesWithGenreResponse : StateFlow<ResponseModel<SeriesListResponse>> = _seriesWithGenreResponse

    init {
        getSeriesWithGenre(1)
    }
    fun getSeriesWithGenre(pageNo: Int){
        viewModelScope.launch{
            repo.getTvListByGenre(genreId, pageNo).collect{
                _seriesWithGenreResponse.value = it
            }
        }
    }
}