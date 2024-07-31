package com.example.moviez.presentation.searchResults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.presentation.commons.personDisplay.PersonListDisplay

@Composable
fun SearchResultPersonList(
    personResponse: PersonListResponse,
    onPersonClick: (Int) -> Unit,
    onPageChange: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (personResponse.results.isEmpty()) {
                NothingFoundScreen()
            } else {
                PersonListDisplay(personResponse = personResponse,
                    onPageChange = onPageChange,
                    onPersonClick = onPersonClick
                )
            }
        }
    }
}