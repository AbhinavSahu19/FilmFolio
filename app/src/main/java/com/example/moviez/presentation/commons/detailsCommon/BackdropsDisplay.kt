package com.example.moviez.presentation.commons.detailsCommon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviez.BuildConfig
import com.example.moviez.R
import com.example.moviez.dataModels.BackdropListItem
import com.example.moviez.dataModels.MovieImages

// Not in use
@Composable
fun BackdropsDisplay(
    images: MovieImages?
) {
    var backdropCard by remember {
        mutableStateOf(true)
    }

    if (images != null && images.backdrops.isNotEmpty()) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .background(colorResource(id = R.color.bg_gray)),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Text(text = "Backdrops")
                    Icon(
                        painter = painterResource(id = R.drawable.drop_down_icon),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                if (backdropCard) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(images.backdrops.chunked(2)) { pair ->
                            Row {
                                pair.forEach { backdrop ->
                                    Card(
                                        colors = CardDefaults.cardColors(
                                            containerColor = colorResource(id = R.color.white)
                                        ),
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier
                                            .width(dimensionResource(id = R.dimen.backdrop_mid_width))
                                            .height(dimensionResource(id = R.dimen.backdrop_mid_height))
                                            .padding(start = 5.dp, end = 3.dp)
                                    ) {
                                        if (backdrop.backdropPath.isNullOrEmpty()) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(colorResource(id = R.color.white)),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.sad_icon),
                                                    contentDescription = "sad_icon",
                                                    modifier = Modifier.size(35.dp)
                                                )
                                                androidx.compose.material.Text(text = "No Image")
                                            }
                                        } else {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(BuildConfig.MID_IMAGE_URL + backdrop.backdropPath)
                                                    .crossfade(true)
                                                    .build(),
                                                contentDescription = "Item Poster",
                                                modifier = Modifier.background(colorResource(id = R.color.black))
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun BackdropsDisplayPre(){
    BackdropsDisplay(
        images = MovieImages(
            backdrops = listOf(BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                BackdropListItem("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),

                )
        )
    )
}