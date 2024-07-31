package com.example.moviez.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.auth.AuthRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object SignInDestination: NavigationDestination{
    override val route = "sign_in"
}

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {
    private val _status = MutableStateFlow(false)
    val status: StateFlow<Boolean> = _status

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            repo.signInWithGoogle(idToken) {
                _status.value = it
            }
        }
    }

    fun resetStatus(){
        _status.value = false
    }
}
