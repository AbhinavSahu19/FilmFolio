package com.example.moviez.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepository
import com.example.moviez.repositary.realtimeDatabase.StorageModel
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

object HomeDestination: NavigationDestination{
    override val route = "home"
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TmdbRepository,
    private val dbRepo: RealtimeDbRepository
): ViewModel() {
    private val _trendingMovies = MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val trendingMovies : StateFlow<ResponseModel<MovieListResponse>> = _trendingMovies

    private val _trendingSeries = MutableStateFlow<ResponseModel<SeriesListResponse>>(ResponseModel.Loading)
    val trendingSeries : StateFlow<ResponseModel<SeriesListResponse>> = _trendingSeries

    private val _storedData = MutableStateFlow<ResponseModel<List<StorageModel>>>(ResponseModel.Loading)
    val storedData: StateFlow<ResponseModel<List<StorageModel>>> = _storedData

    private val _listStatus = MutableStateFlow<ResponseModel<List<String>>>(ResponseModel.Loading)
    val listStatus: StateFlow<ResponseModel<List<String>>> = _listStatus

    private val _addListStatus = MutableStateFlow<ResponseModel<String>>(ResponseModel.Loading)
    val addListStatus: StateFlow<ResponseModel<String>> = _addListStatus

    private val _deleteListStatus = MutableStateFlow<ResponseModel<String>>(ResponseModel.Loading)
    val deleteListStatus: StateFlow<ResponseModel<String>> = _deleteListStatus

    init{
        load()
    }
    fun load(){
        getTrendingMovies()
        getTrendingSeries()
    }
    fun getTrendingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTrendingMovies(1).collect{
                withContext(Dispatchers.Main){ _trendingMovies.value = it }
            }
        }
    }
    fun getTrendingSeries(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTrendingSeries(1).collect{
                withContext(Dispatchers.Main){_trendingSeries.value = it}
            }
        }
    }

    fun getStoredData(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.getData(userId).collect{
                withContext(Dispatchers.Main){_storedData.value = it}
            }
        }
    }
    fun getLists(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.getListNames(userId).collect {
                withContext(Dispatchers.Main) { _listStatus.value = it }
            }
        }
    }
    fun addList(userId: String, listName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.addList(userId, listName).collect {
                withContext(Dispatchers.Main) {
                    _addListStatus.value = it
                }
            }
        }
        getLists(userId)
    }
    fun resetAddListStatus(){
        _addListStatus.value = ResponseModel.Loading
    }
    fun deleteList(userId: String, listName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.deleteList(userId, listName).collect {
                withContext(Dispatchers.Main) {
                    _addListStatus.value = it
                }
            }
        }
        getLists(userId)
    }
}