package com.example.moviez.network

import com.example.moviez.dataModels.GenreList
import com.example.moviez.dataModels.MovieDetails
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.dataModels.SeriesDetails
import com.example.moviez.dataModels.SeriesForSeasons
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.dataModels.SeriesSeasonDetails
import java.util.concurrent.ConcurrentHashMap

object DataCache {
    val MovieDetailCache = HashMap<Int, MovieDetails>()

    val SeriesDetailsCache = HashMap<Int, SeriesDetails>()

    val SeasonDetailsCache = HashMap<String, SeriesSeasonDetails>()

    val  PersonDetailsCache = HashMap<Int, PersonDetails>()

    val GenreListCache = HashMap<Int, GenreList>()

    val SeasonNumbersCache = HashMap<Int, SeriesForSeasons>()

    val MovieListResponseCache = HashMap<String, MovieListResponse>()

    val SeriesListResponseCache = HashMap<String, SeriesListResponse>()

    val PersonListResponseCache = HashMap<String, PersonListResponse>()
}