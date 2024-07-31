package com.example.moviez.presentation.seasonDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.moviez.R
import com.example.moviez.dataModels.SeasonNumberClass

@Composable
fun SeasonChangeBar(
    currentSeasonPtr: Int,
    list: List<SeasonNumberClass>,
    onSeasonChange: (Int) ->Unit,
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        OutlinedButton(onClick = {
            onSeasonChange(list[currentSeasonPtr-1].seasonNumber )
        },
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = colorResource(id = R.color.disabled_color),
                backgroundColor = colorResource(id = R.color.white),
            ),
            enabled = (  currentSeasonPtr > 0)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "prev_arrow",
            )
        }
        OutlinedButton(
            onClick = {
                onSeasonChange( list[currentSeasonPtr+1].seasonNumber)
            },
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = colorResource(id = R.color.disabled_color),
                backgroundColor = colorResource(id = R.color.white)
            ),
            enabled = currentSeasonPtr < list.size-1
        ) {
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "next_arrow"
            )
        }
    }
}
