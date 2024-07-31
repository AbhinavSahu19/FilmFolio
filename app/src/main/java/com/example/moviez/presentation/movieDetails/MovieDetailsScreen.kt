package com.example.moviez.presentation.movieDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.example.moviez.dataModels.BackdropListItem
import com.example.moviez.dataModels.CastListItem
import com.example.moviez.dataModels.Credits
import com.example.moviez.dataModels.GenreListItem
import com.example.moviez.dataModels.MovieDetails
import com.example.moviez.dataModels.MovieImages
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.commons.detailsCommon.DetailCastDisplay
import com.example.moviez.presentation.commons.detailsCommon.DetailsImageCard
import com.example.moviez.presentation.commons.detailsCommon.DetailsText
import com.example.moviez.utils.ResponseModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    navigateToMoviesWithGenre : (Int)->Unit,
    navigateToPersonDetails :(Int)->Unit,
    navigateToMovieDetails :(Int)->Unit,
) {
    val movieDetailsResponse by movieDetailViewModel.movieDetails.collectAsState()

    when (movieDetailsResponse) {
        is ResponseModel.Error -> {
            ErrorScreen(errorMsg = (movieDetailsResponse as ResponseModel.Error).errorMsg,
                onReload = { movieDetailViewModel.getMovieDetails() }
            )
        }

        ResponseModel.Loading -> {
            LoadingScreen()
        }

        is ResponseModel.Success -> {
            MovieDetailsSuccessScreen((movieDetailsResponse as ResponseModel.Success<MovieDetails>).data,
                navigateToMoviesWithGenre ,
            navigateToPersonDetails,
            navigateToMovieDetails)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsSuccessScreen(
    movieDetails: MovieDetails,
    navigateToMoviesWithGenre : (Int)->Unit,
    navigateToPersonDetails :(Int)->Unit,
    navigateToMovieDetails :(Int)->Unit,
) {
    Scaffold(
        topBar = { GeneralTopBar() },
        bottomBar = { GeneralBottomBar() }
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.Center,
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
                    DetailsImageCard(posterPath = movieDetails.posterPath,
                        itemId = movieDetails.movieId,
                        itemType = "m")
                    MoviesSideDetails(
                        adult = movieDetails.adult,
                        popularity = movieDetails.popularity,
                        rating = movieDetails.rating,
                        voteCount = movieDetails.voteCount,
                        runtime = movieDetails.runtime,
                        status = movieDetails.status,
                        releaseDate = movieDetails.releaseDate,
                        budget = movieDetails.budget,
                        revenue = movieDetails.revenue
                    )
                }
            }

            item {
                DetailsText(
                    originalTitle = movieDetails.originalTitle,
                    tagline = movieDetails.tagline,
                    overview = movieDetails.overview,
                    genres = movieDetails.genres,
                    onGenreClick = navigateToMoviesWithGenre
                )
            }
            item {
            }
            item {
                DetailCastDisplay(credits = movieDetails.credits, onCastClick = navigateToPersonDetails)
            }
            item {
                if(movieDetails.similar != null){
                    MoviesSimilar(movieList = movieDetails.similar.results, onMovieClick = navigateToMovieDetails)
                }
            }
        }
    }
}



@Composable
@Preview
fun MovieDetailsSuccessScreenPreview(){
    MovieDetailsSuccessScreen(
        movieDetails = MovieDetails(
            false,
            9000,
            "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg",
            listOf(GenreListItem(0, "Action"),GenreListItem(0, "Adventure"),GenreListItem(0, "Comedy"),GenreListItem(0, "Drama"),GenreListItem(0, "Family"),GenreListItem(0, "Adventure"),GenreListItem(0, "Adventure"),GenreListItem(0, "Adventure")),
            12,
            "en",
            "Deadpool and Wolvorine",
            "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
            90.9,
            "",
            "1999-10-15",
            100853753,
            169,
            "Released",
            "Tagline of movie",
            "Deadpool and Wolvorine",
            7.455,
            121900,
            credits = Credits(
                listOf(CastListItem(0,"Mister Robert Downy Junior", "Iron Man", "sdflkjasdl.jpg"),
                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
                    CastListItem(0,"Robert D Junior", "Iron Man", "sdflkjasdl.jpg"),
                    ),
                ),
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
        ),
        navigateToMoviesWithGenre={},
    navigateToPersonDetails={},
    navigateToMovieDetails ={},
    )
}