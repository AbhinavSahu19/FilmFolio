package com.example.moviez.repositary.tmdb

import com.example.moviez.dataModels.GenreList
import com.example.moviez.dataModels.KeywordListResponse
import com.example.moviez.dataModels.MovieDetails
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.dataModels.SeriesSeasonDetails
import com.example.moviez.dataModels.SeriesDetails
import com.example.moviez.dataModels.SeriesForSeasons
import com.example.moviez.utils.ResponseModel
import kotlinx.coroutines.flow.Flow

interface TmdbRepository {
    fun getMovieGenreList(): Flow<ResponseModel<GenreList>>
    fun getTvGenreList(): Flow<ResponseModel<GenreList>>

    fun getMovieListByGenre(genreId: Int, pageNo: Int): Flow<ResponseModel<MovieListResponse>>
    fun getTvListByGenre(genreId: Int, pageNo: Int): Flow<ResponseModel<SeriesListResponse>>

    fun getNowPlayingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>>
    fun getPopularMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>>
    fun getTopRatedMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>>
    fun getUpcomingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>>
    fun getTrendingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>>

    fun getTrendingSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>>
    fun getPopularSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>>
    fun getTopRatedSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>>

    fun getTrendingPerson(pageNo: Int): Flow<ResponseModel<PersonListResponse>>
    fun getPopularPerson(pageNo: Int): Flow<ResponseModel<PersonListResponse>>

    fun getMovieDetails(movieId: Int): Flow<ResponseModel<MovieDetails>>
    fun getSeriesDetails(seriesId: Int): Flow<ResponseModel<SeriesDetails>>
    fun getSeriesSeasonNumbers(seriesId: Int): Flow<ResponseModel<SeriesForSeasons>>
    fun getSeriesSeasonDetails(seriesId: Int, seasonNo: Int): Flow<ResponseModel<SeriesSeasonDetails>>
    fun getPersonDetails(personId: Int): Flow<ResponseModel<PersonDetails>>

    fun getSearchedKeywords(query: String): Flow<ResponseModel<KeywordListResponse>>
    fun getSearchedMovies(query: String, pageNo: Int): Flow<ResponseModel<MovieListResponse>>
    fun getSearchedSeries(query: String, pageNo: Int): Flow<ResponseModel<SeriesListResponse>>
    fun getSearchedPerson(query: String, pageNo: Int): Flow<ResponseModel<PersonListResponse>>
}