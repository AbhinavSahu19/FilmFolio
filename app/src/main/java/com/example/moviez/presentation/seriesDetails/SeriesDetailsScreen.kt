package com.example.moviez.presentation.seriesDetails

import androidx.compose.foundation.background
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.dataModels.SeriesDetails
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.commons.detailsCommon.DetailCastDisplay
import com.example.moviez.presentation.commons.detailsCommon.DetailsImageCard
import com.example.moviez.presentation.commons.detailsCommon.DetailsText
import com.example.moviez.presentation.genreSeries.GenreSeriesDestination
import com.example.moviez.presentation.personDetails.PersonDetailsDestination
import com.example.moviez.utils.ResponseModel

@Composable
fun SeriesDetailsScreen(
    seriesDetailsViewModel: SeriesDetailsViewModel = hiltViewModel(),
    navigateToSeasonDetails: (Int, Int)-> Unit,
    navigateToSeriesWithGenre :(Int) -> Unit,
    navigateToPersonDetails :(Int) -> Unit,
    navigateToSeriesDetails :(Int) -> Unit,

){
    val seriesDetailsResponse by seriesDetailsViewModel.seriesDetails.collectAsState()

    when (seriesDetailsResponse) {
        is ResponseModel.Error -> {
            ErrorScreen(errorMsg = (seriesDetailsResponse as ResponseModel.Error).errorMsg,
                onReload = { seriesDetailsViewModel.getSeriesDetails() }
            )
        }

        ResponseModel.Loading -> {
            LoadingScreen()
        }

        is ResponseModel.Success -> {
            SeriesDetailsSuccessScreen((seriesDetailsResponse as ResponseModel.Success<SeriesDetails>).data,
                navigateToSeasonDetails,
                navigateToSeriesWithGenre,
                navigateToPersonDetails,
                navigateToSeriesDetails
                )
        }
    }
}

@Composable
fun SeriesDetailsSuccessScreen(
    seriesDetails: SeriesDetails,
    navigateToSeasonDetails: (Int, Int) -> Unit,
    navigateToSeriesWithGenre :(Int) -> Unit,
    navigateToPersonDetails :(Int) -> Unit,
    navigateToSeriesDetails :(Int) -> Unit,
) {
    Scaffold(
        topBar = { GeneralTopBar() },
        bottomBar = { GeneralBottomBar() }
    ) { contentPadding ->
        LazyColumn(
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(colorResource(id = R.color.bg_gray)),
        ) {
            item {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    DetailsImageCard(posterPath = seriesDetails.posterPath,
                        itemId = seriesDetails.seriesId,
                        itemType = "s")
                    SeriesSideDetails(rating = seriesDetails.voteAverage,
                        voteCount = seriesDetails.voteCount,
                        firstAir = seriesDetails.firstAirDate,
                        status = seriesDetails.status,
                        type = seriesDetails.type,
                        adult = seriesDetails.adult,
                        seasons = seriesDetails.numberOfSeasons,
                        episodes = seriesDetails.numberOfEpisodes)
                }
            }
            item {
                DetailsText(
                    originalTitle = seriesDetails.originalName,
                    tagline = seriesDetails.tagline,
                    overview = seriesDetails.overview,
                    genres = seriesDetails.genres,
                    onGenreClick = navigateToSeriesWithGenre
                )
            }
            item {
                SeriesSeasonDisplay(seasons = seriesDetails.seasons,
                    onSeasonClick= {navigateToSeasonDetails(seriesDetails.seriesId, it)})
            }
            item {
                DetailCastDisplay(credits = seriesDetails.credits,
                    onCastClick = navigateToPersonDetails)
            }
            item {
                SeriesSimilar(seriesList = seriesDetails.similar!!.results,
                    onSeriesClick = navigateToSeriesDetails)
            }
        }
    }
}


//@Composable
//@Preview
//fun SeriesDetailsSuccessScreenPreview(){
//    SeriesDetailsSuccessScreen(
//        seriesDetails = SeriesDetails(
//            true,
//            "asdf",
//            "1999-12-11",
//            listOf(GenreListItem(0,"Action"),GenreListItem(0,"Action"),GenreListItem(0,"Action"),GenreListItem(0,"Action"),GenreListItem(0,"Action"),),
//            0,
//            "Game of Thrones",
//            10,
//            76,
//            "en",
//            "Game of Thrones",
//            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
//            "sdfa",
//            listOf(SeriesSeasonListItem()),
//            "In Production",
//            "War is coming",
//            "Scripted",
//            8.438,
//            54645,
//            SeriesListResponse(),
//            Translations(),
//            Credits(
//                listOf(
//                    CastListItem(0,"Mister Robert Downy Junior", "Iron Man", "sdflkjasdl.jpg"),
//                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
//                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
//                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
//                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
//                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
//                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
//                ),
//            )
//        ),
//        navigateToSeasonDetails = {}
//    )
//}