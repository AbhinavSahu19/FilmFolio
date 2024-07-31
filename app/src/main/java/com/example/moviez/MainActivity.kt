package com.example.moviez

import com.example.moviez.utils.Preferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moviez.navigation.NavGraph
import com.example.moviez.presentation.main.MainDestination
import com.example.moviez.presentation.signIn.SignInDestination
import com.example.moviez.ui.theme.MoviezTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = Preferences(this)
        val startDestination = if (preferences.isFirstLaunch()) {
            preferences.setFirstLaunch(false)
            SignInDestination.route
        } else {
            MainDestination.route
        }

        enableEdgeToEdge()
        setContent {
            MoviezTheme {
                NavGraph(startDestination)
//                SignInPage()
            }
        }
    }
}
