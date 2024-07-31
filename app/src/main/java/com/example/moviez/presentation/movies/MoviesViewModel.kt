package com.example.moviez.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

object MoviesDestination: NavigationDestination {
    override val route = "movies"
}
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: TmdbRepository
): ViewModel() {
    private val _trendingMovies= MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val trendingMovies: StateFlow<ResponseModel<MovieListResponse>> = _trendingMovies

    private val _popularMovies= MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val popularMovies: StateFlow<ResponseModel<MovieListResponse>> = _popularMovies

    private val _nowPlayingMovies= MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val nowPlayingMovies: StateFlow<ResponseModel<MovieListResponse>> = _nowPlayingMovies

    private val _topRatedMovies= MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val topRatedMovies: StateFlow<ResponseModel<MovieListResponse>> = _topRatedMovies

    private val _upcomingMovies= MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val upcomingMovies: StateFlow<ResponseModel<MovieListResponse>> = _upcomingMovies

    init {
        getTrendingMovies()
        getPopularMovies()
        getUpcomingMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
    }
    fun getAll(){
        getTrendingMovies()
        getPopularMovies()
        getUpcomingMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
    }
    fun getTrendingMovies(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTrendingMovies(1).collect{
               withContext(Dispatchers.Main) { _trendingMovies.value = it }
            }
        }
    }
    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getPopularMovies(1).collect{
                withContext(Dispatchers.Main) { _popularMovies.value = it }
            }
        }
    }
    fun getNowPlayingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getNowPlayingMovies(1).collect{
                withContext(Dispatchers.Main){ _nowPlayingMovies.value = it }
            }
        }
    }
    fun getTopRatedMovies(){
        viewModelScope.launch (Dispatchers.IO){
            repo.getTopRatedMovies(1).collect{
                withContext(Dispatchers.Main){ _topRatedMovies.value = it }
            }
        }
    }
    fun getUpcomingMovies(){
        viewModelScope.launch (Dispatchers.IO){
            repo.getUpcomingMovies(1).collect{
                withContext(Dispatchers.Main) { _upcomingMovies.value = it }
            }
        }
    }
}