package com.example.moviez.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.dataModels.Keyword

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchKeywordDisplay(
    searchKeyword: List<Keyword>,
    onEnter: (String) -> Unit,
    onArrowClick: (String) -> Unit,
    isSaved: Boolean,
    onRemove: (String)->Unit
    ) {
    var isDialogOpen by remember {
        mutableStateOf(false)
    }
    var keywordString by remember {
        mutableStateOf("")
    }
    val icon = if(isSaved) painterResource(id = R.drawable.searched_icon)
    else painterResource(id = R.drawable.black_search_icon)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(colorResource(id = R.color.bg_gray))
    ) {
        items(Math.min(searchKeyword.size, 10)) { index ->
            val keyword = searchKeyword[index]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 2.dp)
                    .combinedClickable(
                        onClick = { onEnter(keyword.name) },
                        onLongClick = {
                            if (isSaved) isDialogOpen = true
                            keywordString = keyword.name
                        }
                    ),
                contentColor = colorResource(id = R.color.white),
                shape = RoundedCornerShape(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(painter = icon,
                        contentDescription = "search_icon",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(23.dp),
                        colorResource(id = R.color.unselected_gray))
                    Text(
                        text = keyword.name,
                        modifier = Modifier
                            .padding(10.dp, 7.dp)
                            .weight(1f),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.unselected_gray)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.up_left_arrow_icon),
                        contentDescription = "drop_up_arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(18.dp)
                            .clickable {
                                onArrowClick(keyword.name)
                            },
                        colorResource(id = R.color.unselected_gray)
                    )
                }
            }
        }
    }
    if(isDialogOpen){
        AlertDialog(onDismissRequest = {
        isDialogOpen = false
        }, confirmButton = {
            Row {
                Button(
                    onClick = { isDialogOpen = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.bg_gray)
                    )
                ) {
                    Text(
                        text = "Cancel",
                        color = colorResource(id = R.color.unselected_gray)
                    )

                }
                Button(
                    onClick = {onRemove(keywordString)
                              isDialogOpen = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.bg_gray)
                    )
                ) {
                    Text(
                        text = "Remove",
                        color = colorResource(id = R.color.main)
                    )
                }
            }
        },
            containerColor = colorResource(id = R.color.bg_gray),
            text = {
                Column {
                Text(text = keywordString,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold)
                    Text(text = "Remove form search history?",
                        fontSize = 14.sp)
                }
            }
        )
    }
}


@Composable
@Preview
fun SearchKeywordDisplayPreview(){
    SearchKeywordDisplay(
        searchKeyword = listOf(
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
            Keyword( "first"),
        ),
        onArrowClick = {},
        onEnter = {},
        isSaved = true,
        onRemove = {},
    )
}

