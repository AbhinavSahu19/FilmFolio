
package com.example.moviez.presentation.commons.personDisplay

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonListSuccessScreen(
    personResponse: PersonListResponse,
    onPageChange: (Int) -> Unit,
    onPersonClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            GeneralTopBar()
        },
        bottomBar = {
            GeneralBottomBar()
        }
    ) {
        PersonListDisplay(
            personResponse = personResponse,
            onPageChange = onPageChange,
            onPersonClick = onPersonClick
        )
    }
}