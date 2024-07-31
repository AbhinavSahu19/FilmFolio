package com.example.moviez.presentation.seasonDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.dataModels.CastListItem
import com.example.moviez.dataModels.Credits
import com.example.moviez.dataModels.SeasonEpisodesListItem
import com.example.moviez.dataModels.SeasonNumberClass
import com.example.moviez.dataModels.SeriesForSeasons
import com.example.moviez.dataModels.SeriesSeasonDetails
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.commons.detailsCommon.DetailCastDisplay
import com.example.moviez.utils.ResponseModel

@Composable
fun SeriesSeasonScreen(
    seasonViewModel: SeriesSeasonViewModel = hiltViewModel(),
    navigateToPersonDetails: (Int) -> Unit
){
     val seasonDetails by seasonViewModel.seasonDetails.collectAsState()
     val seasonNumbers by seasonViewModel.seasonNumbers.collectAsState()

    when(seasonDetails){
        is ResponseModel.Error -> {
            ErrorScreen(errorMsg = (seasonDetails as ResponseModel.Error).errorMsg,
                onReload = { seasonViewModel.load() })}
        ResponseModel.Loading -> {
            LoadingScreen()}
        is ResponseModel.Success -> {
            when(seasonNumbers){
                is ResponseModel.Error -> {
                    ErrorScreen(errorMsg = (seasonDetails as ResponseModel.Error).errorMsg,
                        onReload = { seasonViewModel.load() })}
                ResponseModel.Loading -> {
                    LoadingScreen()}

                is ResponseModel.Success -> {
                    SeasonDetailsSuccessScreen(
                        (seasonDetails as ResponseModel.Success).data,
                        (seasonNumbers as ResponseModel.Success).data,
                        onSeasonChange = { seasonViewModel.getSeasonDetails(it) },
                        navigateToPersonDetails
                    )
                }
            }
        }
    }
}

@Composable
fun SeasonDetailsSuccessScreen(
    seasonDetails: SeriesSeasonDetails,
    seasonNumbers: SeriesForSeasons,
    onSeasonChange: (Int) -> Unit,
    navigateToPersonDetails: (Int) -> Unit
) {
    Scaffold(
        topBar = { GeneralTopBar()},
        bottomBar = {
            Column {
                SeasonChangeBar(currentSeasonPtr = seasonNumbers.seasons.indexOf(SeasonNumberClass(seasonDetails.seasonNumber)),
                    list = seasonNumbers.seasons,
                    onSeasonChange = {onSeasonChange(it)})
                GeneralBottomBar()
            }
        }
    ) {contentPadding->
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(colorResource(id = R.color.bg_gray)),
        ) {
            item {
                Row (modifier = Modifier.fillMaxWidth()){
                    SeasonDetailsImageCard(posterPath = seasonDetails.posterPath)
                    SeasonSideDetails(
                        seasonNumb = seasonDetails.seasonNumber,
                        rating = seasonDetails.voteAverage,
                        releaseDate = seasonDetails.airDate,
                        episodes = seasonDetails.episodes.size,
                        runtime = getRuntime(seasonDetails.episodes)
                    )
                }
            }
            item{
                SeasonDetailsText(seasonName = seasonDetails.seasonName,
                    overview = seasonDetails.overview)
            }
            item {
                SeasonEpisodesDisplay(
                    seasonDetails.episodes
                )
            }
            item {
                DetailCastDisplay(credits = seasonDetails.credits,
                    onCastClick = navigateToPersonDetails)
            }
        }
    }
}

fun getRuntime(list: List<SeasonEpisodesListItem>): Int{
    var sum=0;
    for(item in list) sum += item.runtime
    return sum
}


@Composable
@Preview
fun SeasonDetailsSuccessScreenPreview() {
    SeasonDetailsSuccessScreen(
        seasonDetails = SeriesSeasonDetails(
            "1999-12-21",
            listOf(
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
            ),
            "Season 1",
            "",
            12,
            "poster_path",
            1,
            9.2,
            234321,
            Credits(
                listOf(CastListItem())
            )
        ),
        onSeasonChange = {},
        seasonNumbers = SeriesForSeasons(
            listOf(
                SeasonNumberClass(12),
                        SeasonNumberClass(12),
                        SeasonNumberClass(12),
                        SeasonNumberClass(12),
                        SeasonNumberClass(12),
                        SeasonNumberClass(12),
                        SeasonNumberClass(12),

            )
        ),
        navigateToPersonDetails = {}

    )
}