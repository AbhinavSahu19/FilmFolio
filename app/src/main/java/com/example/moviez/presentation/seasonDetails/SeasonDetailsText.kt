package com.example.moviez.presentation.seasonDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R

@Composable
fun SeasonDetailsText(
    seasonName: String,
    overview: String
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp, 0.dp, 7.dp, 10.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp, bottom = 10.dp, end = 10.dp)
        ){
            Text(text = seasonName,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 6.dp))
            if(overview.isNotEmpty()){
                Text(text = overview,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 2.dp),
                    lineHeight = 21.sp
                )
            }
        }
    }
}