package com.example.moviez.presentation.Error

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R

@Composable
fun ErrorScreen(
    errorMsg: String,
    onReload: () -> Unit
) {
    val msg= getErrorMessage(errorMsg = errorMsg)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.sad_icon),
            contentDescription = "sad_icon" ,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .size(70.dp)
        )
        Text(
            text = msg,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        Button(
            onClick = onReload,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.main),
            )
        ){
            Text(text = "Reload",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
@Preview
fun ErrorScreenPreview() {
    ErrorScreen(errorMsg = " This is the error that is arising in the system.",
        onReload = {}
    )
}
