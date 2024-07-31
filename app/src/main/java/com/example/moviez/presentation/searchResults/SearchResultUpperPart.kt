package com.example.moviez.presentation.searchResults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R

@Composable
fun SearchResultUpperPart(
    query: String,
    onQueryClick: () -> Unit,
    onCrossClick: () -> Unit,
    option: Int,
    onOptionChange:(Int) -> Unit,
){
    val color0 = if (option==0) colorResource(id = R.color.main)
    else colorResource(id = R.color.bg_gray)
    val color1 = if (option==1) colorResource(id = R.color.main)
    else colorResource(id = R.color.bg_gray)
    val color2 = if (option==2) colorResource(id = R.color.main)
    else colorResource(id = R.color.bg_gray)

    val textColor0 = if (option==0) colorResource(id = R.color.white)
    else colorResource(id = R.color.text)
    val textColor1 = if (option==1) colorResource(id = R.color.white)
    else colorResource(id = R.color.text)
    val textColor2 = if (option==2) colorResource(id = R.color.white)
    else colorResource(id = R.color.text)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.bg_gray)),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(colorResource(id = R.color.bg_gray)),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Icon(painter = painterResource(id = R.drawable.back_arrow),
//                contentDescription = "",
//                modifier = Modifier
//                    .padding(start = 10.dp)
//                    .size(30.dp)
//                    .clickable {
//                        navigateToHome()
//                    })
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable { onQueryClick() },
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                ),
                border = BorderStroke(1.dp, colorResource(id = R.color.main)),
                shape = RoundedCornerShape(7.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp, 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.colored_search_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 9.dp)
                            .size(25.dp)
                    )
                    Text(text = query,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        )
                    Icon(
                        painter = painterResource(id = R.drawable.cross_icon),
                        contentDescription = "cross_icon",
                        modifier = Modifier.clickable { onCrossClick() }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = color0
                ),
                border = BorderStroke(1.dp, colorResource(id = R.color.main)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.clickable {
                    onOptionChange(0)
                }
            ) {
                androidx.compose.material3.Text(
                    text = "Movies",
                    fontSize = 16.sp,
                    color = textColor0,
                    modifier = Modifier
                        .padding(15.dp, 7.dp)
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = color1
                ),
                border = BorderStroke(1.dp, colorResource(id = R.color.main)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.clickable {
                    onOptionChange(1)
                }
            ) {
                androidx.compose.material3.Text(
                    text = "TV Shows",
                    fontSize = 16.sp,
                    color = textColor1,
                    modifier = Modifier
                        .padding(15.dp, 7.dp)
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = color2
                ),
                border = BorderStroke(1.dp, colorResource(id = R.color.main)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.clickable {
                    onOptionChange(2)
                }
            ) {
                androidx.compose.material3.Text(
                    text = "Person",
                    fontSize = 16.sp,
                    color = textColor2,
                    modifier = Modifier
                        .padding(15.dp, 7.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
@Preview
fun SearchResultOptionPreview(){
    SearchResultUpperPart(
        query = "Query",
        onQueryClick = {},
        onCrossClick = {},
        option = 2,
        onOptionChange = {},
    )
}