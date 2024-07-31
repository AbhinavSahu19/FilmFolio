package com.example.moviez.presentation.seasonDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun EpisodeStillCard(
    posterPath: String?
) {
    Card(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.episode_mid_width))
            .height(dimensionResource(id = R.dimen.episode_mid_height))
            .background(colorResource(id = R.color.white))
            .padding(horizontal = 3.dp, vertical = 3.dp)
    ) {
        if (posterPath == "" || posterPath == null) {
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
                Text(text = "No Image")
            }
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.LARGE_IMAGE_URL + posterPath)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Item Poster"
            )
        }
    }
}

@Composable
@Preview
fun EpisodeStillCardPre(){
    EpisodeStillCard("jf")
}