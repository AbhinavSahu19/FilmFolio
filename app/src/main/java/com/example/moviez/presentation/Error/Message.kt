package com.example.moviez.presentation.Error

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.moviez.R

@Composable
fun getErrorMessage(errorMsg: String): String {
    val context = LocalContext.current
    val networkErrorMsg = context.getString(R.string.network_error_msg)

    return when (errorMsg) {
        networkErrorMsg -> {
            "Network Connection Error\nKindly, connect to network and retry"
        }
        else -> errorMsg
    }
}