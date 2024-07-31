package com.example.moviez.presentation.Loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.moviez.R

@Composable
fun HalfLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = colorResource(id = R.color.bg_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp) ,
            color = colorResource(id = R.color.main),
            trackColor = colorResource(id = R.color.white),
        )
    }
}