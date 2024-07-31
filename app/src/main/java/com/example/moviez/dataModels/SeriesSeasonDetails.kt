package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName



data class SeriesSeasonDetails(
//    @field:SerializedName("_id")
//    val seasonId: Int =0,

    @field:SerializedName("air_date")
    val airDate: String ="",

    @field:SerializedName("episodes")
    val episodes: List<SeasonEpisodesListItem> = emptyList(),

    @field:SerializedName("name")
    val seasonName: String ="",

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("id")
    val seasonId: Int =0,

    @field:SerializedName("poster_path")
    val posterPath: String ="",

    @field:SerializedName("season_number")
    val seasonNumber: Int =0,

    @field:SerializedName("vote_average")
    val voteAverage: Double =0.0,

    @field:SerializedName("vote_count")
    val voteCount: Int =0,

    @field:SerializedName("credits")
    val credits: Credits ?= null,
)

data class SeasonEpisodesListItem(
    @field:SerializedName("air_date")
    val airDate: String ="",

    @field:SerializedName("episode_number")
    val episodeNumber: Int =0,

    @field:SerializedName("id")
    val episodeId: Int =0,

    @field:SerializedName("name")
    val episodeName: String ="",

    @field:SerializedName("overview")
    val overview: String ="",

    @field:SerializedName("runtime")
    val runtime: Int =0,

    @field:SerializedName("vote_average")
    val voteAverage: Double =0.0,

    @field:SerializedName("vote_count")
    val voteCount: Int =0,

    @field:SerializedName("still_path")
    val stillPath: String ="",
)


