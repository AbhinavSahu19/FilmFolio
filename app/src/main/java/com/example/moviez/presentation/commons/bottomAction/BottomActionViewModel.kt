package com.example.moviez.presentation.commons.bottomAction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepository
import com.example.moviez.repositary.realtimeDatabase.StorageItem
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BottomActionViewModel @Inject constructor(
    private val repo: RealtimeDbRepository
): ViewModel() {
    private val _listStatus = MutableStateFlow<ResponseModel<List<String>>>(ResponseModel.Loading)
    val listStatus: StateFlow<ResponseModel<List<String>>> = _listStatus

    private val _containItemStatusMap =
        MutableStateFlow<Map<String, ResponseModel<Boolean>>>(emptyMap())
    val containItemStatusMap: StateFlow<Map<String, ResponseModel<Boolean>>> = _containItemStatusMap

    private val _addListStatus = MutableStateFlow<ResponseModel<String>>(ResponseModel.Loading)
    val addListStatus: StateFlow<ResponseModel<String>> = _addListStatus

    private val _addItemStatus =
        MutableStateFlow<ResponseModel<String>>(ResponseModel.Error("Not Started"))
    val addItemStatus: StateFlow<ResponseModel<String>> = _addItemStatus

    private val _deleteItemStatus =
        MutableStateFlow<ResponseModel<String>>(ResponseModel.Error("Not Started"))
    val deleteItemStatus: StateFlow<ResponseModel<String>> = _deleteItemStatus

    fun getLists(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getListNames(userId).collect {
                withContext(Dispatchers.Main) { _listStatus.value = it }
            }
        }
    }

    fun containsItem(userId: String, listName: String, item: StorageItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.containItem(userId, listName, item).collect { response ->
                withContext(Dispatchers.Main) {
                    _containItemStatusMap.value = _containItemStatusMap.value.toMutableMap().apply {
                        put(listName, response)
                    }
                }
            }
        }
    }

    fun addList(userId: String, listName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addList(userId, listName).collect {
                withContext(Dispatchers.Main) {
                    _addListStatus.value = it
                }
            }
        }
        getLists(userId)
    }

    fun addItem(userId: String, listName: String, item: StorageItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addItem(userId, listName, item).collect { response ->
                withContext(Dispatchers.Main) {
                    _addItemStatus.value = response
                    if (response is ResponseModel.Success) {
                        containsItem(userId, listName, item)
                    }
                }
            }
        }
    }

    fun deleteItem(userId: String, listName: String, item: StorageItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteItem(userId, listName, item).collect { response ->
                withContext(Dispatchers.Main) {
                    _deleteItemStatus.value = response
                    if (response is ResponseModel.Success) {
                        containsItem(userId, listName, item)
                    }
                }
            }
        }
    }

    fun resetAddListStatus(){
        _addListStatus.value = ResponseModel.Loading
    }
    // Add reset methods for the statuses
    fun resetAddItemStatus() {
        _addItemStatus.value = ResponseModel.Error("Not Started")
    }

    fun resetDeleteItemStatus() {
        _deleteItemStatus.value = ResponseModel.Error("Not Started")
    }
}