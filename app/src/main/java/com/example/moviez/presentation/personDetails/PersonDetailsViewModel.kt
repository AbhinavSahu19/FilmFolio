package com.example.moviez.presentation.personDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object PersonDetailsDestination: NavigationDestination {
    override val route = "person_details"
    const val personIdArgs = "personId"
    val routeWithArgs = "$route/{$personIdArgs}"
}

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel(){
    private val personId: Int = checkNotNull(savedStateHandle[PersonDetailsDestination.personIdArgs])

    private val _personDetails = MutableStateFlow<ResponseModel<PersonDetails>>(ResponseModel.Loading)
    val personDetails: StateFlow<ResponseModel<PersonDetails>> = _personDetails

    init {
        getPersonDetails()
    }

    fun getPersonDetails() {
        viewModelScope.launch {
            repo.getPersonDetails(personId).collect {
                _personDetails.value = it
            }
        }
    }
}