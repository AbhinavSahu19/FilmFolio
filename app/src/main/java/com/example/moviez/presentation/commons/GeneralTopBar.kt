package com.example.moviez.presentation.commons

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviez.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralTopBar(
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.bg_gray)
        ),
        modifier = Modifier.height(50.dp),
        title = {

        },
        actions = {

        }
    )
}
