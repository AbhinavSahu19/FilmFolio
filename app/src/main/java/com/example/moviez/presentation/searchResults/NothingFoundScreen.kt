package com.example.moviez.presentation.searchResults

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import org.checkerframework.checker.units.qual.C

@Composable
fun NothingFoundScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.nothing_found_icon),
            contentDescription = "nothing_found_icon",
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = "Nothing Found",
            modifier = Modifier.padding(top = 7.dp),
            fontSize = 20.sp
        )
    }
}

@Composable
@Preview
fun NothingFoundScreenPreview(){
    NothingFoundScreen()
}