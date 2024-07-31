package com.example.moviez.presentation.movieList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.MovieListItem
import com.example.moviez.dataModels.MovieListResponse
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

object MovieListDestination: NavigationDestination{
    override val route = "movie-list"
    const val listIdArgs = "list-id"
    val routeWithArgs = "$route/{$listIdArgs}"
}

@HiltViewModel
class MovieListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
) : ViewModel(){
    val listId: Int = checkNotNull(savedStateHandle[MovieListDestination.listIdArgs])

    private val _movieListResponse = MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val movieListResponse: StateFlow<ResponseModel<MovieListResponse>> = _movieListResponse


    init {
        getMovies(1)
    }
    fun getMovies(pageNo: Int){
        when(listId){
            0 -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.getTrendingMovies(pageNo).collect{
                        withContext(Dispatchers.Main) { _movieListResponse.value = it }
                    }
                }
            }
             1 ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repo.getPopularMovies(pageNo).collect{
                        withContext(Dispatchers.Main){ _movieListResponse.value = it }
                    }
                }
            }
             2->{
                viewModelScope.launch (Dispatchers.IO){
                    repo.getNowPlayingMovies(pageNo).collect{
                        withContext(Dispatchers.Main){ _movieListResponse.value = it }
                    }
                }
            }
             3 ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repo.getTopRatedMovies(pageNo).collect{
                        withContext(Dispatchers.Main){ _movieListResponse.value = it }
                    }
                }
            }
            4 ->{
                viewModelScope.launch(Dispatchers.IO) {
                    repo.getUpcomingMovies(pageNo).collect{
                        withContext(Dispatchers.Main){ _movieListResponse.value = it }
                    }
                }
            }
        }
    }
}