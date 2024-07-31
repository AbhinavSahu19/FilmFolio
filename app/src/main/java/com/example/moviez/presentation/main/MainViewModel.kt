package com.example.moviez.presentation.main

import androidx.lifecycle.ViewModel
import com.example.moviez.navigation.NavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object MainDestination: NavigationDestination{
    override val route = "main"
}
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel(){
    private val _screen = MutableStateFlow<Screen>(Screen.Home)
    val screen: StateFlow<Screen> = _screen

    fun onClick(screen : Screen){
        _screen.value = screen
    }
}

sealed class Screen{
    object Home : Screen()
    object Genres: Screen()
    object Movies: Screen()
    object Series: Screen()
    object Settings: Screen()
}