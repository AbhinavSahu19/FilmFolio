package com.example.moviez.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.privacysandbox.ads.adservices.adid.AdId
import com.example.moviez.dataModels.Keyword
import com.example.moviez.dataModels.KeywordListResponse
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepository
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

object SearchDestination: NavigationDestination{
    override val route = "search"
    const val queryArgs = "query"
    val routeWithArgs = "$route/{$queryArgs}"
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHande: SavedStateHandle,
    private val repo: TmdbRepository,
    private val dbRepo: RealtimeDbRepository
) : ViewModel() {
    val query:String = savedStateHande[SearchDestination.queryArgs] ?:""

    private val _searchKeywordResponse = MutableStateFlow<ResponseModel<KeywordListResponse>>(ResponseModel.Loading)
    val searchKeywordResponse: StateFlow<ResponseModel<KeywordListResponse>> = _searchKeywordResponse

    private val _storageKeywordResponse = MutableStateFlow<ResponseModel<List<String>>>(ResponseModel.Loading)
    val storageKeywordResponse: StateFlow<ResponseModel<List<String>>> = _storageKeywordResponse

    private val _addKeywordStatus = MutableStateFlow<ResponseModel<String>>(ResponseModel.Loading)
    val addKeywordStatus : StateFlow<ResponseModel<String>> = _addKeywordStatus

    private val _deleteKeywordStatus = MutableStateFlow<ResponseModel<String>>(ResponseModel.Loading)
    val deleteKeywordStatus : StateFlow<ResponseModel<String>> = _deleteKeywordStatus

    init {
        searchKeyword(query)
    }
    fun searchKeyword(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSearchedKeywords(query).collect {
                withContext(Dispatchers.Main){
                _searchKeywordResponse.value = it}
            }
        }
    }
    fun getStorageKeywords(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.getKeywords(userId).collect {
                withContext(Dispatchers.Main){
                    _storageKeywordResponse.value = it
                }
            }
        }
    }
    fun addKeyword(userId: String, keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.addKeyword(userId, keyword).collect {
                withContext(Dispatchers.Main){
                    _addKeywordStatus.value = it
                    if(it is ResponseModel.Success){
                        getStorageKeywords(userId)
                    }
                }
            }
        }
    }
    fun deleteKeyword(userId: String, keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.deleteKeyword(userId, keyword).collect {
                withContext(Dispatchers.Main){
                    _addKeywordStatus.value = it
//                    if(it is ResponseModel.Success){
//                        getStorageKeywords(userId)
//                    }
                }
            }
        }
    }
}