package com.example.moviez.presentation.commons.itemCards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviez.BuildConfig
import com.example.moviez.R
import com.example.moviez.presentation.commons.bottomAction.BottomActionSheet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MidItemCard(
    posterPath: String?,
    onItemClick: ()->Unit,
    itemId: Int,
    itemType: String
){
    var isModelOpen by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    onItemClick()
                },
                onLongClick = {
                    if (itemType == "m" || itemType == "s") isModelOpen = true
                },
                onClickLabel = "Click to Open Details",
                onLongClickLabel = "Click to Open options"
            )
            .width(dimensionResource(id = R.dimen.mid_width))
            .height(dimensionResource(id = R.dimen.mid_height))
            .background(colorResource(id = R.color.bg_gray))
            .padding(horizontal = 3.dp, vertical = 3.dp)
    ) {
        if(posterPath == "" || posterPath == null){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(painter = painterResource(id = R.drawable.sad_icon),
                    contentDescription = "sad_icon",
                    modifier = Modifier.size(35.dp))
                Text(text = "No Image")
            }
        }
        else{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.MID_IMAGE_URL + posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = "Item Poster"
            )
        }
    }

    if(isModelOpen){
        BottomActionSheet(
            onDismiss = { isModelOpen = false },
            itemId = itemId,
            itemType = itemType,
            posterPath = posterPath?:""
        )
    }
}
