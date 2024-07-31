package com.example.moviez.presentation.Loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviez.R

@Composable
fun LoadingScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
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

@Composable
@Preview
fun LoadingScreenPreview(){
    LoadingScreen()
}