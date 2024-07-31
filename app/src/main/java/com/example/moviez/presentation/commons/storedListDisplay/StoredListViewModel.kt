package com.example.moviez.presentation.commons.storedListDisplay

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepository
import com.example.moviez.repositary.realtimeDatabase.StorageModel
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object StoredListDestination: NavigationDestination{
    override val route = "stored_list"
    const val nameArgs = "list_name"
    val routeWithArgs = "$route/{$nameArgs}"
}

@HiltViewModel
class StoredListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dbRepo: RealtimeDbRepository
) : ViewModel(){
    val listName: String = checkNotNull(savedStateHandle[StoredListDestination.nameArgs])

    private val _dataResponse = MutableStateFlow<ResponseModel<StorageModel>>(ResponseModel.Loading)
    val dataResponse : StateFlow<ResponseModel<StorageModel>> = _dataResponse

    fun getData(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.getDataWithName(userId, listName).collect{
                _dataResponse.value = it
            }
        }
    }
}