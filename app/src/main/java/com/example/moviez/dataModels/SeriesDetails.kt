package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class SeriesDetails(
    @field:SerializedName("adult")
    val adult: Boolean = false,

    @field:SerializedName("backdrop_path")
    val backdropPath: String ="",

    @field:SerializedName("first_air_date")
    val firstAirDate: String = "",

    @field:SerializedName("genres")
    val genres: List<GenreListItem> = emptyList(),

    @field:SerializedName("id")
    val seriesId: Int =0,

    @field:SerializedName("name")
    val name: String ="",

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int =0,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int =0,

    @field:SerializedName("original_languages")
    val originalLanguages: String ="",

    @field:SerializedName("original_name")
    val originalName: String ="",

    @field:SerializedName("overview")
    val overview: String ="",

    @field:SerializedName("poster_path")
    val posterPath: String ="",

    @field:SerializedName("seasons")
    val seasons: List<SeriesSeasonListItem> = emptyList(),

    @field:SerializedName("status")
    val status: String ="",

    @field:SerializedName("tagline")
    val tagline: String ="",

    @field:SerializedName("type")
    val type: String ="",

    @field:SerializedName("vote_average")
    val voteAverage: Double =0.0,

    @field:SerializedName("vote_count")
    val voteCount: Int =0,

    @field:SerializedName("similar")
    val similar: SeriesListResponse ?= null,

    @field:SerializedName("translations")
    val translations: Translations ?= null,

    @field:SerializedName("credits")
    val credits: Credits ?= null,
    )

data class SeriesSeasonListItem(
    @field:SerializedName("air_date")
    val airDate: String ="",

    @field:SerializedName("episode_count")
    val episodeCount: Int =0,

    @field:SerializedName("id")
    val id: Int =0,

    @field:SerializedName("name")
    val name : String ="",

    @field:SerializedName("overview")
    val overview: String ="",

    @field:SerializedName("poster_path")
    val posterPath: String ="",

    @field:SerializedName("season_number")
    val seasonNumber: Int =0,

    @field:SerializedName("vote_average")
    val voteAverage: Double =0.0,
)

