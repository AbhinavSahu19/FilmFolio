package com.example.moviez.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.auth.AuthRepository
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

object SettingDestination: NavigationDestination{
    override val route = "setting"
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authrepo: AuthRepository,
    private val dbRepo: RealtimeDbRepository
): ViewModel() {
    fun signOut() =authrepo.signOut()

    fun delete() =
        authrepo.deleteAccount()

    fun deleteData(userId: String) = dbRepo.deleteData(userId )

}