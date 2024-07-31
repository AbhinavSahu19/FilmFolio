package com.example.moviez.presentation.seasonDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.dataModels.SeasonEpisodesListItem
import com.example.moviez.presentation.commons.formatDateString
import com.example.moviez.presentation.commons.formatTime
import com.example.moviez.presentation.commons.toSingleDecimal

@Composable
fun SeasonEpisodesDisplay(
    episodes: List<SeasonEpisodesListItem>
){
    if(episodes != null && episodes.isNotEmpty()) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 0.dp, 7.dp, 10.dp)
        ) {
            Column {
                Text(
                    text = "Episodes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(15.dp, 5.dp, 15.dp, 0.dp)
                )
                Column {
                    for(episode in episodes){
                        SeasonEpisodesCard(episode = episode)
                    }
                }
            }
        }
    }
}

@Composable
fun SeasonEpisodesCard(
    episode: SeasonEpisodesListItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ),
        border = BorderStroke(1.dp, colorResource(id = R.color.bg_gray))
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp, 3.dp)
        ){
            Row {
                EpisodeStillCard(posterPath = episode.stillPath)
                Column(
                    modifier = Modifier
                        .padding(3.dp, 6.dp)
                        .fillMaxWidth(1f)
                ) {
                    Row {
                        Text(
                            text = "Episode :-  ",
                            fontSize = 16.sp
                        )
                        Text(
                            text = episode.episodeNumber.toString(),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Row {
                        Text(
                            text = "Rating :-  ",
                            fontSize = 16.sp
                        )
                        Text(
                            text = if(episode.voteAverage != 0.0)toSingleDecimal(episode.voteAverage) + "/10" else "--",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Row {
                        Text(text = "Released On:- ",
                            fontSize = 16.sp
                        )
                        Text(text = formatDateString(episode.airDate),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Row {
                        Text(text = "Runtime :- ",
                            fontSize = 16.sp
                        )
                        Text(text = formatTime(episode.runtime),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(6.dp, 3.dp, 2.dp, 2.dp)
            ) {

                Text(text = episode.episodeName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(1.dp))
            }
        }
    }
}



@Composable
@Preview
fun P(){
    SeasonEpisodesDisplay(episodes =
    listOf(
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "The Beast with a Billion Backs",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "overview",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
        SeasonEpisodesListItem(
            "2001-10-10",
            12,
            234,
            "Episode 1",
            "Jon Arryn, the Hand of the King, is dead. King Robert Baratheon plans to ask his oldest friend, Eddard Stark, to take Jon's place. Across the sea, Viserys Targaryen plans to wed his sister to a nomadic warlord in exchange for an army.",
            232,
            8.2,
            2112,
            "rfooeruwor"
        ),
    ),)
}
