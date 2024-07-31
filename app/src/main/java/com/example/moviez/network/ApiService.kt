package com.example.moviez.network

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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // about genres
    @GET("genre/movie/list")
    suspend fun getGenreListMovie(): GenreList

    @GET("genre/tv/list")
    suspend fun getGenreListTv(): GenreList

    //list with genres
    @GET("discover/movie")
    suspend fun getMoviesWithGenre(
        @Query("with_genres")genreId: Int,
        @Query("page")pageNo: Int,
    ): MovieListResponse

    @GET("discover/tv")
    suspend fun getSeriesWithGenre(
        @Query("with_genres")genreId: Int,
        @Query("page")pageNo: Int,
    ): SeriesListResponse

    //general lists
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): MovieListResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): MovieListResponse

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): MovieListResponse

    @GET("trending/tv/day")
    suspend fun getTrendingSeries(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): SeriesListResponse

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): SeriesListResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): SeriesListResponse

    @GET("trending/person/day")
    suspend fun getTrendingPerson(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): PersonListResponse

    @GET("person/popular")
    suspend fun getPopularPerson(
        @Query("language") language: String = "en-US",
        @Query("page")pageNo: Int,
    ): PersonListResponse

    //get details
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "images,credits,similar,translations"
    ): MovieDetails

    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(
        @Path("series_id") seriesId: Int,
        @Query("append_to_response") appendToResponse: String = "similar,translations,credits"
    ): SeriesDetails

    @GET("tv/{series_id}")
    suspend fun getSeriesSeasonNumbers(
        @Path("series_id") seriesId: Int,
    ): SeriesForSeasons

    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getSeriesSeasonDetails(
        @Path("series_id") seriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): SeriesSeasonDetails

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("append_to_response") appendToResponse: String = "images,movie_credits,tv_credits"
    ): PersonDetails

    //search results
    @GET("search/keyword")
    suspend  fun getSearchedKeywords(
        @Query("query") query: String,
        @Query("page") pageNo: Int =1,
    ): KeywordListResponse

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query") query: String,
        @Query("page") pageNo: Int,
    ): MovieListResponse

    @GET("search/tv")
    suspend fun getSearchedSeries(
        @Query("query") query: String,
        @Query("page") pageNo: Int,
    ): SeriesListResponse

    @GET("search/person")
    suspend fun getSearchedPerson(
        @Query("query") query: String,
        @Query("page") pageNo: Int,
    ): PersonListResponse
}