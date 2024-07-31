package com.example.moviez.presentation.personDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviez.R
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.dataModels.PersonImages
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.commons.detailsCommon.DetailsImageCard

@Composable
fun PersonDetailSuccess(
    personDetails: PersonDetails,
    navigateToSeriesDetails :(Int)-> Unit,
    navigateToMovieDetails:(Int)-> Unit
){
    Scaffold(
        topBar = { GeneralTopBar()},
        bottomBar = { GeneralBottomBar()}
    ) {contentPadding->
        LazyColumn (
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.bg_gray)),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
            ){
            item {
                Row {
                    DetailsImageCard(
                        posterPath = personDetails.profilePath,
                        itemId = personDetails.personId,
                        itemType = "p"
                    )
                    PersonSideDetails(
                        department = personDetails.department,
                        gender = personDetails.gender,
                        birthDate = personDetails.birthday,
                        deathDate = personDetails.deathday,
                        birthPlace = personDetails.placeOfBirth
                    )
                }
            }
            item {
                PersonDetailText(name = personDetails.name,
                    biography = personDetails.biography)
            }
            item {
                PersonCastMovie(movieList = personDetails.movieCredits!!.asCast,
                    navigateToMovieDetails)

            }
            item {
                PersonCastSeries(seriesList = personDetails.tvCredits!!.asCast,
                    navigateToSeriesDetails)
            }
        }
    }
}



@Composable
@Preview
fun PersonDetailSuccessPreview(){
    PersonDetailSuccess(
        personDetails = PersonDetails(
            true,
             "Thomas Jeffrey Hanks (born July 9, 1956) is an American actor and filmmaker. Known for both his comedic and dramatic roles, Hanks is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural icon.\n\nHanks made his breakthrough with leading roles in the comedies Splash (1984) and Big (1988). He won two consecutive Academy Awards for Best Actor for starring as a gay lawyer suffering from AIDS in Philadelphia (1993) and a young man with below-average IQ in Forrest Gump (1994). Hanks collaborated with film director Steven Spielberg on five films: Saving Private Ryan (1998), Catch Me If You Can (2002), The Terminal (2004), Bridge of Spies (2015), and The Post (2017), as well as the 2001 miniseries Band of Brothers, which launched him as a director, producer, and screenwriter.\n\nHanks' other notable films include the romantic comedies Sleepless in Seattle (1993) and You've Got Mail (1998); the dramas Apollo 13 (1995), The Green Mile (1999), Cast Away (2000), Road to Perdition (2002), and Cloud Atlas (2012); and the biographical dramas Saving Mr. Banks (2013), Captain Phillips (2013), Sully (2016), and A Beautiful Day in the Neighborhood (2019). He has also appeared as the title character in the Robert Langdon film series, and has voiced Sheriff Woody in the Toy Story film series.\n\nDescription above from the Wikipedia article Tom Hanks, licensed under CC-BY-SA, full list of contributors on Wikipedia.",
            "1999-12-12",
            "2033-12-12",
            0,
            234,
            "Acting",
            "Robert Downy Jr.",
            "California",
            "asdfsadf",
            PersonImages(),

        ),
        navigateToSeriesDetails ={},
    navigateToMovieDetails ={}
    )
}