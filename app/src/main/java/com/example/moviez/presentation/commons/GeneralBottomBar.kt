package com.example.moviez.presentation.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviez.R

@Composable
fun GeneralBottomBar(){
    BottomAppBar(
        containerColor = colorResource(id = R.color.bg_gray),
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
    ){}
}
