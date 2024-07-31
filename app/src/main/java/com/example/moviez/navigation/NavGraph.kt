package com.example.moviez.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviez.presentation.commons.storedListDisplay.StoredDataListScreen
import com.example.moviez.presentation.commons.storedListDisplay.StoredListDestination
import com.example.moviez.presentation.genreMovies.GenreMoviesDestination
import com.example.moviez.presentation.genreMovies.GenreMoviesScreen
import com.example.moviez.presentation.genreSeries.GenreSeriesDestination
import com.example.moviez.presentation.genreSeries.GenreSeriesScreen
import com.example.moviez.presentation.genresList.GenreListDestination
import com.example.moviez.presentation.genresList.GenreListScreen
import com.example.moviez.presentation.home.HomeDestination
import com.example.moviez.presentation.home.HomeScreen
import com.example.moviez.presentation.main.MainDestination
import com.example.moviez.presentation.main.MainScreen
import com.example.moviez.presentation.movieDetails.MovieDetailScreen
import com.example.moviez.presentation.movieDetails.MovieDetailsDestination
import com.example.moviez.presentation.movieList.MovieListDestination
import com.example.moviez.presentation.movieList.MovieListScreen
import com.example.moviez.presentation.movies.MovieScreen
import com.example.moviez.presentation.movies.MoviesDestination
import com.example.moviez.presentation.personDetails.PersonDetailsDestination
import com.example.moviez.presentation.personDetails.PersonDetailsScreen
import com.example.moviez.presentation.search.SearchDestination
import com.example.moviez.presentation.search.SearchScreen
import com.example.moviez.presentation.searchResults.SearchResultDestination
import com.example.moviez.presentation.searchResults.SearchResultScreen
import com.example.moviez.presentation.series.SeriesDestination
import com.example.moviez.presentation.series.SeriesScreen
import com.example.moviez.presentation.seriesDetails.SeriesDetailsDestination
import com.example.moviez.presentation.seriesDetails.SeriesDetailsScreen
import com.example.moviez.presentation.seriesList.SeriesListDestination
import com.example.moviez.presentation.seriesList.SeriesListScreen
import com.example.moviez.presentation.seasonDetails.SeriesSeasonDestination
import com.example.moviez.presentation.seasonDetails.SeriesSeasonScreen
import com.example.moviez.presentation.settings.SettingDestination
import com.example.moviez.presentation.settings.SettingScreen
import com.example.moviez.presentation.signIn.SignInDestination
import com.example.moviez.presentation.signIn.SignInPage

@Composable
fun NavGraph(
    startDestination: String,
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController,
        startDestination = startDestination) {
        //Main Screen
        composable(route = MainDestination.route){
            MainScreen(
                navController = navController,
            )
        }
        //Home Screen
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it") },
                navigateTosSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it") },
                navigateToStoredList = {navController.navigate("${StoredListDestination.route}/$it")},
                navigateToSignIn = {navController.navigate(SignInDestination.route)}
            )
        }
        //Genre Screen
        composable(route = GenreListDestination.route) {
            GenreListScreen(
                navigateToSeriesListWithGenre = { navController.navigate("${GenreSeriesDestination.route}/$it")} ,
                navigateToMovieListWithGenre = { navController.navigate("${GenreMoviesDestination.route}/$it") },
            )
        }
        //Movies with Genre
        composable(GenreMoviesDestination.routeWithArgs,
            arguments = listOf(navArgument(GenreMoviesDestination.genreIdArgs){type = NavType.IntType})
        ){
            GenreMoviesScreen (
                navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it")}
            )
        }
        //Series with Genre
        composable(GenreSeriesDestination.routeWithArgs,
            arguments = listOf(navArgument(GenreSeriesDestination.genreIdArgs){type = NavType.IntType})
        ){
            GenreSeriesScreen(
                navigateToSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it")}
            )
        }
        //Movie screen
        composable(route = MoviesDestination.route){
            MovieScreen(
                navigateToMovieDetails = {navController.navigate("${MovieDetailsDestination.route}/$it")},
                navigateToMovieListWithNumb = {navController.navigate("${MovieListDestination.route}/$it")}
            )
        }
        //Movie List Screen
        composable(route = MovieListDestination.routeWithArgs,
            arguments = listOf(navArgument(MovieListDestination.listIdArgs){type = NavType.IntType})
        ){
            MovieListScreen(
                navigateToMovieDetails = {navController.navigate("${MovieDetailsDestination.route}/$it")},
            )
        }
        //Series Screen
        composable(route = SeriesDestination.route){
            SeriesScreen(navigateToSeriesListWithNumb = {navController.navigate("${SeriesListDestination.route}/$it")},
                navigateToSeriesDetails = {navController.navigate("${SeriesDetailsDestination.route}/$it")}
            )

        }
        //Series List Screen
        composable(route = SeriesListDestination.routeWithArgs,
            arguments = listOf(navArgument(SeriesListDestination.seriesIdArgs){type = NavType.IntType})
        ){
            SeriesListScreen (
                navigateToSeriesDetails = {navController.navigate("${SeriesDetailsDestination.route}/$it")}            )
        }
        //Movie Details Screen
        composable(route = MovieDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(MovieDetailsDestination.movieIdArgs){type = NavType.IntType})
        ){
            MovieDetailScreen(
                navigateToMoviesWithGenre = { navController.navigate("${GenreMoviesDestination.route}/$it") },
                navigateToPersonDetails = { navController.navigate("${PersonDetailsDestination.route}/$it") },
                navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it") }

            )
        }
        //Series Details Screen
        composable(route = SeriesDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(SeriesDetailsDestination.seriesIdArgs){type = NavType.IntType})
        ){
            SeriesDetailsScreen(
                navigateToSeasonDetails = { seriesId, seasonNumb ->
                    navController.navigate("${SeriesSeasonDestination.route}/$seriesId/$seasonNumb")
                },
                navigateToSeriesWithGenre = { navController.navigate("${GenreSeriesDestination.route}/$it") },
                navigateToPersonDetails = { navController.navigate("${PersonDetailsDestination.route}/$it") },
                navigateToSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it") }

            )
        }
        //Series Season Screen
        composable(
            SeriesSeasonDestination.routeWithArgs,
            arguments = listOf(
                navArgument(SeriesSeasonDestination.seriesIdArgs){type = NavType.IntType},
                navArgument(SeriesSeasonDestination.seasonNumbArgs){type = NavType.IntType}
            )
        ){
            SeriesSeasonScreen(
                navigateToPersonDetails = { navController.navigate("${PersonDetailsDestination.route}/$it") },
            )
        }
        //Person Details Screen
        composable(route = PersonDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(PersonDetailsDestination.personIdArgs){type = NavType.IntType})
        ){
            PersonDetailsScreen(
                navigateToSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it") },
                navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it") }
            )
        }


        //Setting Screen
        composable(SettingDestination.route){
            SettingScreen(
                navigateToSignIn = {navController.navigate(SignInDestination.route)}
            )
        }

        //Search screen
        composable(
            route = SearchDestination.route + "?${SearchDestination.queryArgs}={${SearchDestination.queryArgs}}",
            arguments = listOf(navArgument(SearchDestination.queryArgs) {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            SearchScreen(
                navigateToSearchResult = { navController.navigate("${SearchResultDestination.route}/$it")}
            )
        }
        //Search Result
        composable(SearchResultDestination.routeWithArgs,
            arguments = listOf(navArgument(SearchResultDestination.queryArgs){type = NavType.StringType
                defaultValue = ""})
        ){
            SearchResultScreen(
                navigateToSearch = {  val encodedQuery = Uri.encode(it)
                    navController.navigate("${SearchDestination.route}?${SearchDestination.queryArgs}=$encodedQuery")},
                navigateToHome = {
                    navController.popBackStack(MainDestination.route, false)
                                 },
                navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it")},
                navigateToSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it")},
                navigateToPersonDetails = { navController.navigate("${PersonDetailsDestination.route}/$it")}
            )
        }

        //Sign In Screen
        composable(SignInDestination.route){
            SignInPage(
                navigateToMain = {navController.navigate(MainDestination.route)}
            )
        }

        //Display of List stored in database
        composable(StoredListDestination.routeWithArgs,
            arguments = listOf(navArgument(StoredListDestination.nameArgs){type = NavType.StringType})
        ){
            StoredDataListScreen( navigateToMovieDetails = { navController.navigate("${MovieDetailsDestination.route}/$it")},
               navigateToSeriesDetails = { navController.navigate("${SeriesDetailsDestination.route}/$it")},)
        }
    }
}