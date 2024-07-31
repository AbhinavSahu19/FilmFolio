package com.example.moviez.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.presentation.commons.GeneralTopBar



@Composable
fun MainSearchBar(
    navigateToSearch: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(colorResource(id = R.color.bg_gray)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    navigateToSearch()
                },
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.main)
            ),
            shape = RoundedCornerShape(7.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp, 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 9.dp)
                        .size(25.dp)
                )
                Text(
                    text = "Search Here",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.white))
            }
        }
    }

}

@Composable
@Preview
fun MainSearchBarPreview(){
    MainSearchBar(
        navigateToSearch = {}
    )
}