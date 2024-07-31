package com.example.moviez.presentation.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.moviez.R

@Composable
fun ListPageChangeBar(
    currentPage: Int,
    totalPage: Int,
    onPageChange: (Int) ->Unit,
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        OutlinedButton(onClick = {
            onPageChange(currentPage - 1 )
        },
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = colorResource(id = R.color.disabled_color),
                backgroundColor = colorResource(id = R.color.white),
            ),
            enabled = (  currentPage> 1)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "prev_arrow",
            )
        }
        Text(text = "Page ${currentPage} of ${totalPage}")
        OutlinedButton(
            onClick = {
                onPageChange( currentPage + 1)
            },
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = colorResource(id = R.color.disabled_color),
                backgroundColor = colorResource(id = R.color.white)
            ),
            enabled = currentPage < totalPage
        ) {
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "next_arrow"
            )
        }
    }
}