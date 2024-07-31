package com.example.moviez.presentation.commons.detailsCommon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.dataModels.Credits

@Composable
fun DetailCastDisplay(
    credits: Credits?,
    onCastClick:(Int) -> Unit
){
    if(credits != null && credits.cast.isNotEmpty()){
        Card (
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 0.dp, 7.dp, 10.dp)
        ){
            Column {
                Text(text = "Cast",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(15.dp, 5.dp,15.dp, 0.dp)
                )
                LazyRow(
                    modifier = Modifier.padding(5.dp)
                ) {
                    items(credits.cast){cast->
                        Card (
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.white)
                            ),
                            border = BorderStroke(1.dp, colorResource(id = R.color.bg_gray)),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .padding(start = 5.dp, end = 3.dp)
                                .clickable { onCastClick(cast.castId) }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                DetailCastImageCard(cast.castProfilePath)
                                Text(text = cast.castName,
                                    fontSize = 15.sp,
                                    color = colorResource(id = R.color.text),
                                    modifier = Modifier.padding(start = 3.dp, end = 3.dp))
                                Text(text = cast.castCharacter,
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.unselected_gray),
                                    modifier = Modifier.padding(start = 3.dp, bottom = 3.dp, end = 3.dp))
                            }
                        }
                    }
                }
            }

        }
    }
}