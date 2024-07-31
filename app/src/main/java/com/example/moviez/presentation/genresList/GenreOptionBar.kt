package com.example.moviez.presentation.genresList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R

@Composable
fun GenreOptionBar(
    option: Boolean,
    onChange: (Boolean) -> Unit
) {
    val color = if (option) colorResource(id = R.color.bg_gray)
    else colorResource(id = R.color.main)
    val opColor = if (option) colorResource(id = R.color.main)
    else colorResource(id = R.color.bg_gray)

    val textColor = if (option) colorResource(id = R.color.text)
    else colorResource(id = R.color.white)
    val opTextColor = if (option) colorResource(id = R.color.white)
    else colorResource(id = R.color.text)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
//            .padding(20.dp)
            .background(color = colorResource(id = R.color.bg_gray)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = color
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.main)),
            shape = RectangleShape,
            modifier = Modifier.clickable {
                onChange(false)
            }
        ) {
            Text(
                text = "Movies",
                fontSize = 17.sp,
                color = textColor,
                modifier = Modifier
                    .padding(40.dp, 7.dp)
            )
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = opColor
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.main)),
            shape = RectangleShape,
            modifier = Modifier.clickable {
                onChange(true)
            }
        ) {
            Text(
                text = "TV Shows",
                fontSize = 17.sp,
                color = opTextColor,
                modifier = Modifier
                    .padding(40.dp, 7.dp)
            )
        }
    }
}

@Composable
@Preview
fun GenreOptionPreview() {
    GenreOptionBar(
        option = false,
        onChange = {})
}